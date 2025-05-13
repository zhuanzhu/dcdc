package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.SoEnquiryReadService;
import com.egeo.components.order.service.write.SoEnquiryWriteService;


@Component
public class SoEnquiryFacade {
	
	@Resource
	private SoEnquiryReadService soEnquiryReadService;
	
	@Resource
	private SoEnquiryWriteService soEnquiryWriteService;
	


}
	