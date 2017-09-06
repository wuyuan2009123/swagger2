package com.swagger.security;

import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;

import javax.servlet.ServletContext;

/**
 * Created by wuy on 2017/9/5.
 */

@Order(100)
public class SecurityWebApplicationInitializer  extends AbstractSecurityWebApplicationInitializer {
    @Override
    protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
    }
}
