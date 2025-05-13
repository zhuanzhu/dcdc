package com.egeo.components.pay.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.pay.manage.read.PayAliLogReadManage;
import com.egeo.components.pay.service.read.PayAliLogReadService;

@Service("payAliLogReadService")
public class PayAliLogReadServiceImpl  implements PayAliLogReadService {
	@Autowired
	private PayAliLogReadManage payAliLogReadManage;



}
	