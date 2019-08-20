package com.spring.boot.common.bean;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author yuderen
 * @version 2019/7/10 9:10
 */
public interface BaseBeanMapper<T> extends Mapper<T> {

    List<T> fetchRecordList(T t);

}
