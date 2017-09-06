package com.swagger.security;

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

	@Value("${redis.host}")
	private String host;

	@Value("${redis.port}")
	private String port;



	@Bean
	public JedisConnectionFactory connectionFactory() {
		JedisShardInfo jedisShardInfo=new JedisShardInfo(host,Integer.valueOf(port));
		return new JedisConnectionFactory(jedisShardInfo);
	}
}
