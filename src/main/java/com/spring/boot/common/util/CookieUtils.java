package com.spring.boot.common.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author yuderen
 * @version 2018/3/6 15:23
 */
public class CookieUtils {

    private static final Logger log = LoggerFactory.getLogger(CookieUtils.class);

    public CookieUtils() {
    }

    public static Cookie getCookie(String name) {
        Cookie[] cookies = SpringContextUtil.getRequest().getCookies();
        if(cookies != null && cookies.length > 0) {
            Cookie[] arr$ = cookies;
            int len$ = cookies.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                Cookie c = arr$[i$];
                if(c.getName().equals(name)) {
                    return c;
                }
            }
        }

        return null;
    }

    public static String getCookieString(Cookie cookie) {
        if(null == cookie) {
            return null;
        } else {
            try {
                String value = URLDecoder.decode(cookie.getValue(), "UTF-8");
                return value;
            } catch (Exception var2) {
                log.warn(var2.toString());
                return "";
            }
        }
    }

    public static String getCookieString(String name) {
        Cookie cookie = getCookie(name);
        return getCookieString(cookie);
    }

    public static Cookie addCookie(String name, String value, Integer expiry, String domain) {
        try {
            value = URLEncoder.encode(value, "UTF-8");
        } catch (Exception var8) {
            log.error(var8.toString());
        }

        Cookie cookie = new Cookie(name, value);
        if(expiry != null) {
            cookie.setMaxAge(expiry.intValue());
        }

        if(StringUtils.isNotBlank(domain)) {
            cookie.setDomain(domain);
        }

        String ctx = SpringContextUtil.getRequest().getContextPath();
        cookie.setPath(StringUtils.isBlank(ctx)?"/":ctx);
        SpringContextUtil.getResponse().addCookie(cookie);
        return cookie;
    }

    public static void cancleCookie(String name, String domain) {
        Cookie cookie = new Cookie(name, "");
        cookie.setMaxAge(0);
        String ctx = SpringContextUtil.getRequest().getContextPath();
        cookie.setPath(StringUtils.isBlank(ctx)?"/":ctx);
        if(StringUtils.isNotBlank(domain)) {
            cookie.setDomain(domain);
        }
        SpringContextUtil.getResponse().addCookie(cookie);
    }

}
