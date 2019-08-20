package com.spring.boot.common.bean;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseController {

    public ResponseData successResponse(Object data){
        return new ResponseData(data);
    }

    public ResponseData errorResponse(String code, String msg){
        return new ResponseData(code, msg);
    }

    public ResponseData errorResponse(String msg){
        return new ResponseData("0001", msg);
    }


    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(LocalDateTime.class, new LocalDateTimeEditor());
    }

    private class LocalDateTimeEditor extends PropertyEditorSupport {

        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            if (StringUtils.isEmpty(text)){
                return;
            }
            Pattern pattern = Pattern.compile("[0-9]+");
            Matcher result = pattern.matcher(text);
            if (result.matches()){
                Long dateTime = Long.parseLong(text);
                Instant instant = Instant.ofEpochMilli(dateTime);
                LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                String temp = localDateTime.toString();
                System.out.println(temp);
                setValue(localDateTime);
            }
        }

        @Override
        public String getAsText() {
            return getValue().toString();
        }
    }

}
