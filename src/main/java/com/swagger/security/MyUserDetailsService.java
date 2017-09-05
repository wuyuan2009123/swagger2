package com.swagger.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by wuy on 2017/9/5.
 */

@Component("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("-----------------------------------> MyUserDetailsService  ");
        return new User("zhangsan","111111", AuthorityUtils.createAuthorityList("ROLE_admin","xxxx"));
    }
}
