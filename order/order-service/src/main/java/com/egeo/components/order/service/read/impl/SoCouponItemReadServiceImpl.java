package com.egeo.components.order.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.read.SoCouponItemReadService;
import com.egeo.components.order.manage.read.SoCouponItemReadManage;

@Service("soCouponItemReadService")
public class SoCouponItemReadServiceImpl  implements SoCouponItemReadService {
	@Autowired
	private SoCouponItemReadManage soCouponItemReadManage;
}
	