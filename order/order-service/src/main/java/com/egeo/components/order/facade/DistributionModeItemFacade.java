package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.DistributionModeItemReadService;
import com.egeo.components.order.service.write.DistributionModeItemWriteService;


@Component
public class DistributionModeItemFacade {
	
	@Resource
	private DistributionModeItemReadService distributionModeItemReadService;
	
	@Resource
	private DistributionModeItemWriteService distributionModeItemWriteService;
	


}
	