package com.spring.boot.common.util;

import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yuderen
 * @version 2019/7/10 9:39
 */
public class PageUtils {

    public static void page(Integer pageNum, Integer pageSize) {
        int pageNumTemp = 1;
        int pageSizeTemp = 40;
        if (pageNum != null && pageSize != null) {
            if (pageNum != 0) {
                pageNumTemp = pageNum;
            }

            if (pageSize != 0) {
                pageSizeTemp = pageSize;
            }
        }

        PageHelper.startPage(pageNumTemp, pageSizeTemp);
    }

    public static void initQueryPageInfo(){
        Integer pageNum = getParamPage("pageNum");
        Integer pageSize = getParamPage("numPerPage");
        page(pageNum, pageSize);
    }

    private static Integer getParamPage(String paramName){
        HttpServletRequest request = SpringContextUtil.getRequest();
        String value = request.getHeader(paramName);
        if (StringUtils.isEmpty(value)){
            value = request.getParameter(paramName);
        }
        return StringUtils.isEmpty(value) ? null : Integer.parseInt(value);
    }

}
