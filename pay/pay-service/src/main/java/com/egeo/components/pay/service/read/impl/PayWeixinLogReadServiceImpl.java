package com.egeo.components.pay.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.pay.converter.PayWeixinLogConverter;
import com.egeo.components.pay.dto.PayWeixinLogDTO;
import com.egeo.components.pay.manage.read.PayWeixinLogReadManage;
import com.egeo.components.pay.service.read.PayWeixinLogReadService;

@Service("payWeixinLogReadService")
public class PayWeixinLogReadServiceImpl  implements PayWeixinLogReadService {
	@Autowired
	private PayWeixinLogReadManage payWeixinLogReadManage;

	@Override
	public PayWeixinLogDTO queryPayWeixinLogByOrderCode(String orderCode) {
		
		return PayWeixinLogConverter.toDTO(payWeixinLogReadManage.queryPayWeixinLogByOrderCode(orderCode));
	}



}
	