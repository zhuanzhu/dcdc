package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.CustomerConditionManage;
import com.egeo.components.order.facade.CustomerConditionFacade;

@Service("customerCondition")
public class CustomerConditionManageImpl implements CustomerConditionManage{

	
	@Resource(name="customerConditionFacade")
	private CustomerConditionFacade customerConditionFacade;
	


}
	