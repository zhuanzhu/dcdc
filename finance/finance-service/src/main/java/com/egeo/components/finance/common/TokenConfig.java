package com.egeo.components.finance.common;

import com.egeo.token.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by guofeng.qin on 2017/8/3 0003.
 */
@Configuration
public class TokenConfig {
    @Bean
    public TokenManager getTokenManager(@Autowired RedisTemplate<String, byte[]> redisTemplate, @Value("${token.redis.dbIndex}") int dbIndex) {
        return new TokenManager(redisTemplate, dbIndex);
    }
}
