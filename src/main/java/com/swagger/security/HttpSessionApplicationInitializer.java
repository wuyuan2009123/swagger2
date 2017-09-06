package com.swagger.security;

import org.springframework.core.annotation.Order;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

/**
 * Created by wuy on 2017/9/6.
 */

@Order(99)
public class HttpSessionApplicationInitializer extends AbstractHttpSessionApplicationInitializer {

}
