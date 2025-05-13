package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.DistributionModeItemManage;
import com.egeo.components.order.facade.DistributionModeItemFacade;

@Service("distributionModeItem")
public class DistributionModeItemManageImpl implements DistributionModeItemManage{

	
	@Resource(name="distributionModeItemFacade")
	private DistributionModeItemFacade distributionModeItemFacade;
	


}
	