package com.egeo.components.pay.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.pay.dto.PayWeixinLogDTO;

@FeignClient(name = "service-pay-fgj",contextId="PayWeixinLogClient")
public interface PayWeixinLogClient {

	/**
	 * 通过订单查询微信支付日志
	 * @param orderCode
	 * @return
	 */
	@RequestMapping(value = { "/client/pay/payWeixinLog/queryPayWeixinLogByOrderCode" }, method = { RequestMethod.POST }) 
	public PayWeixinLogDTO queryPayWeixinLogByOrderCode(String orderCode);
}
