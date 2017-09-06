package com.swagger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wuy on 2017/9/6.
 */
public class CookieUtil {
    public static void addCookie(String name ,String value,String domain,
                                 int maxage,String path, HttpServletResponse response){
        Cookie cookie = new Cookie(name,value);
        if(domain!=null){
            cookie.setDomain(domain);
        }
        cookie.setMaxAge(maxage);
        cookie.setPath(path);
        response.addCookie(cookie);
    }

    public static void addCookie(String name ,String value,String domain,
                                 int maxage, HttpServletResponse response){
        addCookie(name, value,domain, maxage, "localhost" , response);
    }

    public static String getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return null;
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals(name)) {
                return cookies[i].getValue();
            }
        }
        return null;
    }

    public static void removeCookie(String name, String domain, HttpServletRequest request, HttpServletResponse response) {
        String cookieVal = getCookie(request,name);
        if(cookieVal!=null){
            CookieUtil.addCookie(name, null, domain, 0, response);
        }
    }

    public static void removeCookie(String name, HttpServletRequest request, HttpServletResponse response) {
        CookieUtil.removeCookie(name, "localhost", request, response);
    }




}
