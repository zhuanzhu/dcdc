package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.RefundmentWriteService;
import com.egeo.components.order.manage.write.RefundmentWriteManage;

@Service("refundmentWriteService")
public class RefundmentWriteServiceImpl  implements RefundmentWriteService {
	@Autowired
	private RefundmentWriteManage refundmentWriteManage;
}
	