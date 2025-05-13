package com.egeo.components.pay.service.read;


import com.egeo.components.pay.dto.PayWeixinLogDTO;

public interface PayWeixinLogReadService {

	/**
	 * 通过订单查询微信支付日志
	 * @param orderCode
	 * @return
	 */
	PayWeixinLogDTO queryPayWeixinLogByOrderCode(String orderCode);



}
	