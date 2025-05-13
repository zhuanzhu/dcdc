package com.egeo.components.user.common;

import com.egeo.utils.lock.LockManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by jinjin on 2017/9/14 0009.
 */
@Configuration
@ConfigurationProperties(prefix = "spring.jedis")
public class LockConfig {

    @Autowired
    private RedisTemplate<String, byte[]> redisTemplate;

    private int dbIndex;

    public int getDbIndex() {
        return dbIndex;
    }

    public void setDbIndex(int dbIndex) {
        this.dbIndex = dbIndex;
    }

    @Bean
    public LockManager getLockManager() {
//      @Value("${spring.jedis.dbIndex}")
        return new LockManager(dbIndex, redisTemplate);
    }

}

