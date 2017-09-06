package com.swagger.session;


import com.swagger.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

@Deprecated
public class RedisDao {

    private final static Logger logger = LoggerFactory.getLogger(RedisDao.class);

    private final static JedisPool jedisPool;

    static{
        String host=System.getProperty("host","127.0.0.1");
        String port=System.getProperty("port","6379");
        jedisPool= new JedisPool(host, Integer.valueOf(port));
    }

    private RedisDao(){}

    public static Map<String,Object> getSession(String sessionId){
        Map<String,Object> session=new HashMap<String,Object>();
        Jedis jedis=null;
        try{
            jedis = jedisPool.getResource();
            String json=jedis.get(sessionId);
            if(!StringUtils.isEmpty(json)){
                session = JsonUtils.decode(json, Map.class);
            }
        }catch (Exception e){
            logger.error("获取session异常"+e.getMessage()+e.getCause());
        }
        finally {
            jedis.close();
        }
        return session;
    }


    public static void saveSession(String sessionId,Map<String,Object> map){
        Jedis jedis=null;
        try{
            jedis = jedisPool.getResource();
            String json= JsonUtils.encode(map);
            jedis.set(sessionId,json);
        }catch (Exception e){
            logger.error("存储session异常"+e.getMessage()+e.getCause());
        }finally {
            jedis.close();
        }

    }

    public static void removeSession(String sessionId){
        Jedis jedis=null;
        try{
            jedis = jedisPool.getResource();
            jedis.del(sessionId);
        }catch (Exception e){
            logger.error("移除session异常"+e.getMessage()+e.getCause());
        }finally {
            jedis.close();
        }
    }



}