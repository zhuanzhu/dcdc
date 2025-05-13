package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.SoCouponReadService;
import com.egeo.components.order.service.write.SoCouponWriteService;


@Component
public class SoCouponFacade {
	
	@Resource
	private SoCouponReadService soCouponReadService;
	
	@Resource
	private SoCouponWriteService soCouponWriteService;
	


}
	