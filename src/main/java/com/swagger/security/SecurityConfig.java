package com.swagger.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by wuy on 2017/9/5.
 */


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private BookShopAuthenticationSuccessHandler bookShopAuthenticationSuccessHandler;

    @Autowired
    private BookShopAuthenticationFailureHandler bookShopAuthenticationFailureHandler;

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/css/**","/js/**","/img/**","/font-awesome/**");
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/", "/login").permitAll()
                .anyRequest().access("@bookSecurity.check(authentication,request)")
                .and()
                .exceptionHandling().accessDeniedPage("/Access_Denied");


//        http.formLogin()
//                .and()
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/webjars/**",
//                        "/login", "/book", "/login.html", "/regist.html",
//                        "/auth/**", "/getRegistUserInfo","/registUser", "/session.html")
//                .permitAll()
//                .anyRequest()
//                .access("@bookSecurity.check(authentication,request)");
    }
}



