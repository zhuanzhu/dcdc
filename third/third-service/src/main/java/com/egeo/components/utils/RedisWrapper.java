package com.egeo.components.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

import java.util.function.Function;

/**
 * Created by guofeng.qin on 2017/6/6 0006.
 *
 * Reids 封装工具类, 依赖 jedis
 */
public class RedisWrapper {
    private JedisPool jedisPool;

    public RedisWrapper(String host, int port, String password) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(3);
        jedisPoolConfig.setSoftMinEvictableIdleTimeMillis(20 * 60 * 1000L);

        jedisPool = new JedisPool(jedisPoolConfig, host, port, Protocol.DEFAULT_TIMEOUT, password);
    }

    public RedisWrapper(String host, int port, String password, JedisPoolConfig config) {
    	jedisPool = new JedisPool(config, host, port, Protocol.DEFAULT_TIMEOUT, password);
    }

    /**
     * maxActive: 链接池中最大连接数,默认为8.
     * maxIdle: 链接池中最大空闲的连接数,默认为8.
     * minIdle: 连接池中最少空闲的连接数,默认为0.
     * maxWait: 当连接池资源耗尽时，调用者最大阻塞的时间，超时将跑出异常。单位，毫秒数;默认为-1.表示永不超时.
     * minEvictableIdleTimeMillis: 连接空闲的最小时间，达到此值后空闲连接将可能会被移除。负值(-1)表示不移除。
     * softMinEvictableIdleTimeMillis: 连接空闲的最小时间，达到此值后空闲链接将会被移除，且保留“minIdle”个空闲连接数。默认为-1.
     * numTestsPerEvictionRun: 对于“空闲链接”检测线程而言，每次检测的链接资源的个数。默认为3.
     * testOnBorrow: 向调用者输出“链接”资源时，是否检测是有有效，如果无效则从连接池中移除，并尝试获取继续获取。默认为false。建议保持默认值.
     * testOnReturn: 向连接池“归还”链接时，是否检测“链接”对象的有效性。默认为false。建议保持默认值.
     * testWhileIdle: 向调用者输出“链接”对象时，是否检测它的空闲超时；默认为false。如果“链接”空闲超时，将会被移除。建议保持默认值.
     * timeBetweenEvictionRunsMillis: “空闲链接”检测线程，检测的周期，毫秒数。如果为负值，表示不运行“检测线程”。默认为-1.
     * whenExhaustedAction: 当“连接池”中active数量达到阀值时，即“链接”资源耗尽时，连接池需要采取的手段, 默认为1：
     * -> 0 : 抛出异常，
     * -> 1 : 阻塞，直到有可用链接资源
     * -> 2 : 强制创建新的链接资源
     */
    public RedisWrapper(JedisPoolConfig config, String host, int port, String password, int timeout) {
        jedisPool = new JedisPool(config, host, port, timeout, password);
    }

    public Jedis getJedis() {
        checkResource();
        return jedisPool.getResource();
    }

    public <R> R doWith(Function<Jedis, R> func) {
        checkResource();
        try (Jedis jedis = jedisPool.getResource()) {
            return func.apply(jedis);
        }
    }

    private void checkResource() {
        if (jedisPool == null || jedisPool.isClosed()) {
            throw new RuntimeException("Jedis Error, jedis is not init or closed !!!");
        }
    }
}
