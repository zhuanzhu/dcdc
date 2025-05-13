package com.egeo.components.utils;

import com.alibaba.fastjson.JSONObject;
import com.egeo.utils.log.XLogger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

import java.util.concurrent.*;

/**
 * Created by guofeng.qin on 2017/5/14 0014.
 */
public class RedisLogstash {
    private static final XLogger logger = XLogger.getLogger(RedisLogstash.class);

    private static final int DefaultQueueSize = 20 * 10000;
    private Executor executor;

    private String redisHost;
    private int redisPort;
    private String redisPassword;
    private int threadNum;
    private String logKey;
    private int logDBIndex;

    private JedisPool jedisPool;

    public RedisLogstash(String redisHost, int redisPort, String redisPassword, int threadNum, String logKey, int logDBIndex) {
        this.redisHost = redisHost;
        this.redisPort = redisPort;
        this.redisPassword = redisPassword;
        this.threadNum = threadNum;
        this.logKey = logKey;
        this.logDBIndex = logDBIndex;

        init();
    }

    private void init() {
        /*
        maxActive: 链接池中最大连接数,默认为8.
        maxIdle: 链接池中最大空闲的连接数,默认为8.
        minIdle: 连接池中最少空闲的连接数,默认为0.
        maxWait: 当连接池资源耗尽时，调用者最大阻塞的时间，超时将跑出异常。单位，毫秒数;默认为-1.表示永不超时.
        minEvictableIdleTimeMillis: 连接空闲的最小时间，达到此值后空闲连接将可能会被移除。负值(-1)表示不移除。
        softMinEvictableIdleTimeMillis: 连接空闲的最小时间，达到此值后空闲链接将会被移除，且保留“minIdle”个空闲连接数。默认为-1.
        numTestsPerEvictionRun: 对于“空闲链接”检测线程而言，每次检测的链接资源的个数。默认为3.
        testOnBorrow: 向调用者输出“链接”资源时，是否检测是有有效，如果无效则从连接池中移除，并尝试获取继续获取。默认为false。建议保持默认值.
        testOnReturn:  向连接池“归还”链接时，是否检测“链接”对象的有效性。默认为false。建议保持默认值.
        testWhileIdle:  向调用者输出“链接”对象时，是否检测它的空闲超时；默认为false。如果“链接”空闲超时，将会被移除。建议保持默认值.
        timeBetweenEvictionRunsMillis:  “空闲链接”检测线程，检测的周期，毫秒数。如果为负值，表示不运行“检测线程”。默认为-1.
         whenExhaustedAction: 当“连接池”中active数量达到阀值时，即“链接”资源耗尽时，连接池需要采取的手段, 默认为1：
         -> 0 : 抛出异常，
         -> 1 : 阻塞，直到有可用链接资源
         -> 2 : 强制创建新的链接资源
         */
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(3);
        jedisPoolConfig.setSoftMinEvictableIdleTimeMillis(10 * 60 * 1000L);
        jedisPool = new JedisPool(jedisPoolConfig, redisHost, redisPort, Protocol.DEFAULT_TIMEOUT, StringUtil.isEmpty(redisPassword) ? null : redisPassword);

        BlockingQueue<Runnable> queue = new LinkedBlockingDeque<>(DefaultQueueSize);
        executor = new ThreadPoolExecutor(threadNum, threadNum, 0L, TimeUnit.MILLISECONDS, queue, (r, executor) -> {
            // Message Rejected
            if (r instanceof LogRunnable) {
                LogRunnable lr = (LogRunnable) r;
                String key = lr.getKey();
                JSONObject log = lr.getLog();
                logError(key, log);
            } else {
                logger.error("Log4j2RedisAppender Unknown Runnable");
            }
        });
    }

    public void append(JSONObject log) {
        try {
            log.put("thread", Thread.currentThread().getName());
            executor.execute(new LogRunnable(this, jedisPool, logDBIndex, logKey, log));
        } catch (Exception e) {
            logError(logKey, log);
            throw e;
        }
    }

    public void append(String key, Integer db, JSONObject log) {
        try {
            executor.execute(new LogRunnable(this, jedisPool, db, key, log));
        } catch (Exception e) {
            logError(key, log);
            throw e;
        }
    }

    private void logError(String key, JSONObject log) {
        logger.error("Unable to write to Redis content: [{}][{}]", key, log == null ? "null" : log.toJSONString());
    }

    private static class LogRunnable implements Runnable {
        private RedisLogstash redisLogstash;
        private JedisPool jedisPool;
        private int logDBIndex;
        private String key;
        private JSONObject log;

        public LogRunnable(RedisLogstash redisLogstash, JedisPool jedisPool, int logDBIndex, String key, JSONObject log) {
            this.redisLogstash = redisLogstash;
            this.jedisPool = jedisPool;
            this.logDBIndex = logDBIndex;
            this.key = key;
            this.log = log;
        }

        @Override
        public void run() {
            try {
                String message = (log == null ? "{}" : log.toJSONString());
                try (Jedis jedis = jedisPool.getResource()) {
                    jedis.select(logDBIndex);
                    jedis.rpush(key, message);
                }
            } catch (Exception e) {
                redisLogstash.logError(key, getLog());
            }
        }

        public JSONObject getLog() {
            return log;
        }

        public String getKey() {
            return key;
        }
    }
}
