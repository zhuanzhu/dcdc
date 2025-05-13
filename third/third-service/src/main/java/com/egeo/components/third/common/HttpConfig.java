package com.egeo.components.third.common;

import com.egeo.utils.http.HttpClientManager;
import com.egeo.utils.http.XRHttp;
import com.egeo.utils.log.XLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by guofeng.qin on 2017/8/5 0005.
 */
@Configuration
public class HttpConfig {
    private static final XLogger logger = XLogger.getLogger(HttpConfig.class);

    @Bean
    public XRHttp getXRHttp(@Value("${http.maxPreRoute}") int maxPreRoute, @Value("${http.maxTotal}") int maxTotal) {
        try {
            HttpClientManager httpClientManager = new HttpClientManager(maxPreRoute, maxTotal);
            XRHttp xrHttp = new XRHttp(httpClientManager);
            return xrHttp;
        } catch (Exception e) {
            logger.error("HttpClient 初始化错误！！！", e);
            throw new RuntimeException(e);
        }
    }
}
