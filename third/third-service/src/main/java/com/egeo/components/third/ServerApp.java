package com.egeo.components.third;

import com.egeo.utils.log.XLogger;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by guofeng.qin on 2017/4/25 0025.
 * <p>
 */
@EnableFeignClients(basePackages = { "com.egeo.components" })
@EnableDiscoveryClient
@EnableScheduling
@SpringBootApplication(scanBasePackages = { "com.egeo", "org.springframework.cloud.netflix.feign" })
public class ServerApp implements ApplicationContextAware {
	private static final XLogger logger = XLogger.getLogger(ServerApp.class);

	private static ApplicationContext ctx;

	public static ApplicationContext ctx() {
		return ctx;
	}

	public static void main(String[] args) throws Exception {
		System.setProperty("org.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES", "false");

		SpringApplication.run(ServerApp.class, args);

		start(args);

		logger.info("ServerEnd....");
	}

	public static void start(String... args) throws Exception {
		// 初始化DB
		// DbManager.init("mybatis.cfg.xml");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ctx = applicationContext;
	}

}
