package com.egeo.components.utils;

import java.util.function.Function;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by guofeng.qin on 2017/6/6 0006.
 */
public class RedisManager {
    private static RedisWrapper redisWrapper;

    public static void init(String host, int port, String password) {
    	JedisPoolConfig config = new JedisPoolConfig();
    	config.setMaxTotal(100);
    	config.setMaxIdle(20);
    	config.setMinIdle(8);
    	config.setMaxWaitMillis(10 * 1000L); // 10 seconds
    	config.setMinEvictableIdleTimeMillis(60 * 1000L); // 1 mins
    	config.setTimeBetweenEvictionRunsMillis(30 * 1000L); // 30 seconds
        redisWrapper = new RedisWrapper(host, port, password, config);
    }

    public static Jedis getJedis() {
        return redisWrapper.getJedis();
    }

    public static <R> R doWith(Function<Jedis, R> func) {
        return redisWrapper.doWith(func);
    }
}
