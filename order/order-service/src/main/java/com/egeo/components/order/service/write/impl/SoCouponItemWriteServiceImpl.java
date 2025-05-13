package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.SoCouponItemWriteService;
import com.egeo.components.order.manage.write.SoCouponItemWriteManage;

@Service("soCouponItemWriteService")
public class SoCouponItemWriteServiceImpl  implements SoCouponItemWriteService {
	@Autowired
	private SoCouponItemWriteManage soCouponItemWriteManage;
}
	