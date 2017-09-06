package com.swagger.session;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wuy on 2017/9/6.
 */
@Deprecated
public class DistributionSessionRequestFilter extends OncePerRequestFilter {


    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            if((request instanceof HttpServletRequest) && (response instanceof HttpServletResponse)){
                filterChain.doFilter(new DistributionSessionRequestWrapper(request,response),response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
