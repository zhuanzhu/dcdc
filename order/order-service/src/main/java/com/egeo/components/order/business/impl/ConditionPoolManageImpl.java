package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.ConditionPoolManage;
import com.egeo.components.order.facade.ConditionPoolFacade;

@Service("conditionPool")
public class ConditionPoolManageImpl implements ConditionPoolManage{

	
	@Resource(name="conditionPoolFacade")
	private ConditionPoolFacade conditionPoolFacade;
	


}
	