package com.egeo.components.user.service;

import javax.jms.TextMessage;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.egeo.components.user.business.LoginManage;
import com.egeo.components.user.dto.InfoDTO;
import com.egeo.components.user.service.write.InfoWriteService;
import com.egeo.utils.ActiveMQUtils;
import com.egeo.utils.log.XLogger;

/**
 * ActiveMQ监听入口
 *
 * @author teng
 * @date 2018年1月4日
 */
@Service
@Order(2)
public class ActiveMQListenerService implements CommandLineRunner {

	private static final XLogger LOG = XLogger.getLogger(ActiveMQListenerService.class);

	@Value("${mq.queueName_sendInfo}")
	private String queueNameSendInfo;
	@Value("${mq.queueName_wechatAuth}")
	private String queueNameWechatAuth;
	@Value("${mq.delay.time}")
	private Long mqDelayTime;

    @Autowired
    private LoginManage loginManage;
	@Autowired
	private InfoWriteService infoWriteService;
	@SuppressWarnings("deprecation")
	public void receive(String name, long delayTime) {
		try {
			ActiveMQUtils.getConsumer(name).start(message -> {
				String msg = null;
				try {
					LOG.info("消息name:{}", name);
					msg = ((TextMessage) message).getText().toString();
					LOG.info("开始获取监听消息:{}", msg);
					if (StringUtils.isEmpty(msg)) {
						return true;
					}
					InfoDTO dto = JSON.parseObject(msg, InfoDTO.class);
					infoWriteService.sendInfoWithTx(dto);
				} catch (Exception e) {
					e.printStackTrace();
					LOG.error("MQ消费异常：", e.getMessage(), e);
					try {
						ActiveMQUtils.getSender(name).sendSchedule(msg, delayTime);
					} catch (Exception e1) {
						LOG.error("MQ消息回滚异常", e1.getMessage(), e1);
					}
				}
				return true;
			});
		} catch (Exception e) {
			LOG.error("开始监听异常", e);
		}
	}

	@SuppressWarnings("deprecation")
	public void receive2(String name, long delayTime) {
		try {
			ActiveMQUtils.getConsumer(name).start(message -> {
				String msg = null;
				try {
					LOG.info("消息name:{}", name);
					msg = ((TextMessage) message).getText().toString();
					LOG.info("开始获取监听消息:{}", msg);
					if (StringUtils.isEmpty(msg)) {
						return true;
					}
					LOG.info("接收队列消息：临时授权码："+msg);
					loginManage.saveWorkWechatAuthInfo(msg);
				} catch (Exception e) {
					e.printStackTrace();
					LOG.error("MQ消费异常：", e.getMessage(), e);
					try {
						ActiveMQUtils.getSender(name).sendSchedule(msg, delayTime);
					} catch (Exception e1) {
						LOG.error("MQ消息回滚异常", e1.getMessage(), e1);
					}
				}
				return true;
			});
		} catch (Exception e) {
			LOG.error("开始监听异常", e);
		}
	}
	
	@SuppressWarnings("deprecation")
	public void receive3(String name, long delayTime) {
		try {
			ActiveMQUtils.getConsumer(name).start(message -> {
				String msg = null;
				try {
					LOG.info("消息name:{}", name);
					msg = ((TextMessage) message).getText().toString();
					LOG.info("开始获取监听消息:{}", msg);
					if (StringUtils.isEmpty(msg)) {
						return true;
					}

				} catch (Exception e) {
					e.printStackTrace();
					LOG.error("MQ消费异常：", e.getMessage(), e);
					try {
						ActiveMQUtils.getSender(name).sendSchedule(msg, delayTime);
					} catch (Exception e1) {
						LOG.error("MQ消息回滚异常", e1.getMessage(), e1);
					}
				}
				return true;
			});
		} catch (Exception e) {
			LOG.error("开始监听异常", e);
		}
	}


	@Override
	public void run(String... arg0) throws Exception {

		LOG.info("start ActiveMQ listener");
		long delayTime = mqDelayTime;

		// 监听消息
		receive(queueNameSendInfo, delayTime);
		// 监听消息
		receive2(queueNameWechatAuth, delayTime);
		// 监听消息
		//receive3(queueNameUserAuth, delayTime);
	}
}
