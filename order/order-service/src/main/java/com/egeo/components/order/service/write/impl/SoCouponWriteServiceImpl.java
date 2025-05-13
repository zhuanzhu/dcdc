package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.SoCouponWriteService;
import com.egeo.components.order.manage.write.SoCouponWriteManage;

@Service("soCouponWriteService")
public class SoCouponWriteServiceImpl  implements SoCouponWriteService {
	@Autowired
	private SoCouponWriteManage soCouponWriteManage;
}
	