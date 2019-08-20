package com.spring.boot.common.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.boot.common.annotation.validation.Validation;
import com.spring.boot.common.util.IdGenerator;
import lombok.Data;

import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * @author yuderen
 * @version 2019/7/9 16:22
 */
@Data
public class BaseBean {

    @Id
    @Validation
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long gid;
    private Integer recordStatus;
    private Long createUser;
    private Long updateUser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    public void initCreateInfo(Long userId){
        this.gid = IdGenerator.generateId();
        initCreateInfoNotGid(userId);
    }

    public void initCreateInfoNotGid(Long userId){
        this.recordStatus = 101;
        if (null != userId){
            this.createUser = userId;
        }
        this.createTime = LocalDateTime.now();
        initUpdateInfo(userId);
    }

    public void initUpdateInfo(Long userId){
        if (null != userId){
            this.updateUser = userId;
        }
        this.updateTime = LocalDateTime.now();
    }

}
