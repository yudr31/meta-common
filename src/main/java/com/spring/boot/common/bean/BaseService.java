package com.spring.boot.common.bean;

import com.spring.boot.common.util.SpringContextUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yuderen
 * @version 2019/7/12 18:16
 */
public class BaseService {

    public static String loginUserId(){
        HttpServletRequest request = SpringContextUtil.getRequest();
        String userId = request.getParameter("userId");
        if (StringUtils.isEmpty(userId)){
            userId = request.getHeader("userId");
        }
        return userId;
    }

}
