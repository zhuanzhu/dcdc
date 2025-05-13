package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.SoTrackManage;
import com.egeo.components.order.facade.SoTrackFacade;

@Service("soTrack")
public class SoTrackManageImpl implements SoTrackManage{

	
	@Resource(name="soTrackFacade")
	private SoTrackFacade soTrackFacade;
	


}
	