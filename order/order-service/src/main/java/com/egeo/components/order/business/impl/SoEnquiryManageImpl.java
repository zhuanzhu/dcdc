package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.SoEnquiryManage;
import com.egeo.components.order.facade.SoEnquiryFacade;

@Service("soEnquiry")
public class SoEnquiryManageImpl implements SoEnquiryManage{

	
	@Resource(name="soEnquiryFacade")
	private SoEnquiryFacade soEnquiryFacade;
	


}
	