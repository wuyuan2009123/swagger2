package com.swagger.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import redis.clients.jedis.JedisShardInfo;

/**
 * Created by xin on 15/1/23.
 */
@Configuration
@EnableRedisHttpSession
public class SessionConfig {

	@Autowired
	private JedisConnectionFactory jedisConnectionFactory;


	@Bean
	public JedisConnectionFactory connectionFactory() {
		return jedisConnectionFactory;
	}
}
