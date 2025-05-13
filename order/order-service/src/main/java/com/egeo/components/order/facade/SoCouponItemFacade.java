package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.SoCouponItemReadService;
import com.egeo.components.order.service.write.SoCouponItemWriteService;


@Component
public class SoCouponItemFacade {
	
	@Resource
	private SoCouponItemReadService soCouponItemReadService;
	
	@Resource
	private SoCouponItemWriteService soCouponItemWriteService;
	


}
	