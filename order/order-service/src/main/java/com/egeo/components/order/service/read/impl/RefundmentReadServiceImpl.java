package com.egeo.components.order.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.read.RefundmentReadService;
import com.egeo.components.order.manage.read.RefundmentReadManage;

@Service("refundmentReadService")
public class RefundmentReadServiceImpl  implements RefundmentReadService {
	@Autowired
	private RefundmentReadManage refundmentReadManage;
}
	