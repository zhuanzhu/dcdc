package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.SoTrackReadService;
import com.egeo.components.order.service.write.SoTrackWriteService;


@Component
public class SoTrackFacade {
	
	@Resource
	private SoTrackReadService soTrackReadService;
	
	@Resource
	private SoTrackWriteService soTrackWriteService;
	


}
	