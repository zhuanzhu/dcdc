package com.egeo.components.pay.manage.read.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.pay.dao.read.PayWeixinLogReadDAO;
import com.egeo.components.pay.manage.read.PayWeixinLogReadManage;
import com.egeo.components.pay.po.PayWeixinLogPO;


@Service
public class PayWeixinLogReadManageImpl implements PayWeixinLogReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PayWeixinLogReadDAO payWeixinLogReadDAO;
	
	@Override
	public PayWeixinLogPO queryPayWeixinLogByOrderCode(String orderCode) {
		
		return payWeixinLogReadDAO.queryPayWeixinLogByOrderCode(orderCode);
	}
	


	
}
	