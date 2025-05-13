package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.SoCouponItemManage;
import com.egeo.components.order.facade.SoCouponItemFacade;

@Service("soCouponItem")
public class SoCouponItemManageImpl implements SoCouponItemManage{

	
	@Resource(name="soCouponItemFacade")
	private SoCouponItemFacade soCouponItemFacade;
	


}
	