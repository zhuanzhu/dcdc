package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.RefundmentManage;
import com.egeo.components.order.facade.RefundmentFacade;

@Service("refundment")
public class RefundmentManageImpl implements RefundmentManage{

	
	@Resource(name="refundmentFacade")
	private RefundmentFacade refundmentFacade;
	


}
	