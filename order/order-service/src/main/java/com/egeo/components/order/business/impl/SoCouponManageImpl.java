package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.SoCouponManage;
import com.egeo.components.order.facade.SoCouponFacade;

@Service("soCoupon")
public class SoCouponManageImpl implements SoCouponManage{

	
	@Resource(name="soCouponFacade")
	private SoCouponFacade soCouponFacade;
	


}
	