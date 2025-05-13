package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.RefundmentReadService;
import com.egeo.components.order.service.write.RefundmentWriteService;


@Component
public class RefundmentFacade {
	
	@Resource
	private RefundmentReadService refundmentReadService;
	
	@Resource
	private RefundmentWriteService refundmentWriteService;
	


}
	