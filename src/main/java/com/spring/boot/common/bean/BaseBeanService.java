package com.spring.boot.common.bean;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.boot.common.annotation.validation.ValidationExecutor;
import com.spring.boot.common.util.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@Slf4j
public abstract class BaseBeanService<T1 extends BaseBeanMapper<T2>, T2 extends BaseBean> {

    protected static final Integer ACTIVITY_STATUS = 101;
    protected static final Integer REMOVE_STATUS = 103;

    @Autowired
    private T1 baseMapper;

    public PageInfo<T2> fetchRecordPageInfo(T2 t2){
        PageUtils.initQueryPageInfo();
        return new PageInfo(fetchRecordList(t2));
    }

    public List<T2> fetchRecordList(T2 t2){
        return baseMapper.fetchRecordList(t2);
    }

    public T2 fetchRecordByGid(Long gid){
        return baseMapper.selectByPrimaryKey(gid);
    }

    public Integer insertSelective(T2 t2){
        String beanName = t2.getClass().getSimpleName();
        log.info("开始添加{}记录信息",beanName);
        int count = 0;
        try {
            t2.initCreateInfo(null);
            count += baseMapper.insertSelective(t2);
        } catch (Exception e){
            log.error("添加{}记录信息失败！{}:{}",beanName,beanName,t2);
            log.error("添加信息出现错误", e);
        }
        log.info("结束添加{}记录信息",beanName);
        return count;
    }

    public Integer updateSelectiveByKey(T2 t2){
        String beanName = t2.getClass().getSimpleName();
        log.info("开始更新{}记录信息",beanName);
        validate(t2);
        int count = 0;
        try {
            t2.initUpdateInfo(null);
            count += baseMapper.updateByPrimaryKeySelective(t2);
        } catch (Exception e){
            log.error("更新{}记录信息失败！{}:{}",beanName,beanName,t2);
            log.error("更新信息出现错误", e);
        }
        log.info("结束更新{}记录信息",beanName);
        return count;
    }

    public Integer batchRemove(List<Long> gidList){
        int count = 0;
        for (Long gid : gidList){
            count += removeRecord(gid);
        }
        return count;
    }

    public Integer removeRecord(Long gid){
        T2 record = newInstance();
        record.setGid(gid);
        record.setRecordStatus(103);
        return updateSelectiveByKey(record);
    }

    private void validate(T2 t2){
        String[] fields = {"gid"};
        ValidationExecutor.notNullValidate(fields,t2);
    }

    private T2 newInstance(){
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        Class<T2> clazz = (Class<T2>) pt.getActualTypeArguments()[1];
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            log.info("获取实例化对象失败");
        }
        throw new RuntimeException("获取实例化对象失败");
    }

}
