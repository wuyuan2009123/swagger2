package com.swagger.security;


import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 * Created by wuy on 2017/9/5.
 */

@Component("bookShopAuthenticationFailureHandler")
public class BookShopAuthenticationFailureHandler implements AuthenticationFailureHandler {

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.AuthenticationException e) throws IOException, ServletException {
        response.getWriter().print(e.getMessage());
    }
}
