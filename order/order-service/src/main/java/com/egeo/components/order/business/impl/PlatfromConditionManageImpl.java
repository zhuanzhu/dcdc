package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.PlatfromConditionManage;
import com.egeo.components.order.facade.PlatfromConditionFacade;

@Service("platfromCondition")
public class PlatfromConditionManageImpl implements PlatfromConditionManage{

	
	@Resource(name="platfromConditionFacade")
	private PlatfromConditionFacade platfromConditionFacade;
	


}
	