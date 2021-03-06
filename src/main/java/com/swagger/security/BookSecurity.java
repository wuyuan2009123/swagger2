package com.swagger.security;

/**
 * Created by wuy on 2017/9/5.
 */
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component("bookSecurity")
public class BookSecurity {

    public boolean check(Authentication authentication, HttpServletRequest request) {
        System.out.println("-----------------------------------> BookSecurity  ");

        String url= request.getRequestURI();
        System.err.println(url);

        if( "/".equals(url)  || "/login".equals(url) ||  url.contains("/css") || url.contains("/img") || url.contains("/font-awesome") || url.contains("/js")){
            return true;
        }
        Object principal = authentication.getPrincipal();
        if(!(principal instanceof UserDetails)){
            return false;
        }
        System.err.println(" BookSecurity principal : "+principal);
        if (principal != null && principal instanceof UserDetails) {
            System.err.println(((UserDetails) principal).getAuthorities());
            //在这里  判断 url 是否在 和 你的 资源url 匹配, 如果 匹配的话 就授权,否则 就 return false
        }
        return true;
    }
}

