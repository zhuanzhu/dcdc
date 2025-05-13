package com.egeo.components.config.service;

import com.alibaba.fastjson.JSON;
import com.egeo.components.config.ServerApp;
import com.egeo.components.config.converter.LogConverter;
import com.egeo.components.config.service.write.LogWriteService;
import com.egeo.log.EgeoLog;
import com.egeo.utils.log.XLogger;
import com.egeo.utils.ActiveMQUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.jms.TextMessage;

/**
 * ActiveMQ监听入口
 *
 * @author teng
 * @date 2018年1月4日
 */
@Service
@Order(2)
public class ActiveMQListenerService implements CommandLineRunner {

	private static final XLogger logger = XLogger.getLogger(ActiveMQListenerService.class);

	@Value("${mq.queueName_logging}")
	private String queueNameLogging;
	@Value("${mq.delay.time}")
	private Long mqDelayTime;
	@Autowired
	private LogWriteService logWriteService;
	@SuppressWarnings("deprecation")
	public void receive(String name, long delayTime) {
		try {
			ActiveMQUtils.getConsumer(name).start(message -> {
				String msg = null;
				try {
					logger.info("消息name:{}", name);
					msg = ((TextMessage) message).getText().toString();
					logger.info("开始获取监听消息:{}", msg);
					if (StringUtils.isEmpty(msg)) {
						return true;
					}
					// 处理消息
					EgeoLog log = JSON.parseObject(msg, EgeoLog.class);
				    this.logWriteService.insertLogWithTx(LogConverter.egeoLogtoDTO(log));
				} catch (Exception e) {
					logger.error("MQ消费异常：", e.getMessage(), e);
					try {
						ActiveMQUtils.getSender(name).sendSchedule(msg, delayTime);
					} catch (Exception e1) {
						logger.error("MQ消息回滚异常", e1.getMessage(), e1);
					}
				}
				return true;
			});
		} catch (Exception e) {
			logger.error("开始监听异常", e);
		}
	}/*

	@SuppressWarnings("deprecation")
	public void receive2(String name, long delayTime) {
		try {
			ActiveMQUtils.getConsumer(name).start(message -> {
				String msg = null;
				try {
					logger.info("消息name:{}", name);
					msg = ((TextMessage) message).getText().toString();
					logger.info("开始获取监听消息:{}", msg);
					if (StringUtils.isEmpty(msg)) {
						return true;
					}
					// 处理征信查询补偿
					authorizationService.invokeCredit(msg);

				} catch (Exception e) {
					logger.error("MQ消费异常：", e.getMessage(), e);
					try {
						ActiveMQUtils.getSender(name).sendSchedule(msg, delayTime);
					} catch (Exception e1) {
						logger.error("MQ消息回滚异常", e1.getMessage(), e1);
					}
				}
				return true;
			});
		} catch (Exception e) {
			logger.error("开始监听异常", e);
		}
	}
	
	@SuppressWarnings("deprecation")
	public void receive3(String name, long delayTime) {
		try {
			ActiveMQUtils.getConsumer(name).start(message -> {
				String msg = null;
				try {
					logger.info("消息name:{}", name);
					msg = ((TextMessage) message).getText().toString();
					logger.info("开始获取监听消息:{}", msg);
					if (StringUtils.isEmpty(msg)) {
						return true;
					}
					// 处理
					authorizationService.invokeUserAuth(msg);

				} catch (Exception e) {
					logger.error("MQ消费异常：", e.getMessage(), e);
					try {
						ActiveMQUtils.getSender(name).sendSchedule(msg, delayTime);
					} catch (Exception e1) {
						logger.error("MQ消息回滚异常", e1.getMessage(), e1);
					}
				}
				return true;
			});
		} catch (Exception e) {
			logger.error("开始监听异常", e);
		}
	}
*/

	@Override
	public void run(String... arg0) throws Exception {

		logger.info("start ActiveMQ listener");
		long delayTime = mqDelayTime;

		// 监听消息
		receive(queueNameLogging, delayTime);
		/*// 监听消息
		receive2(queueNameCreditAuth, delayTime);
		// 监听消息
		receive3(queueNameUserAuth, delayTime);*/
	}
}
