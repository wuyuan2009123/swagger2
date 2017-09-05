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
        System.err.println(request.getRequestURI());
        Object principal = authentication.getPrincipal();
        System.err.println(" BookSecurity principal : "+principal);
        if (principal != null && principal instanceof UserDetails) {
            System.err.println(((UserDetails) principal).getAuthorities());
        }
        return true;
    }
}

