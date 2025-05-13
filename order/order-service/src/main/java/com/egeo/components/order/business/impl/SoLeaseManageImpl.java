package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.SoLeaseManage;
import com.egeo.components.order.facade.SoLeaseFacade;

@Service("soLease")
public class SoLeaseManageImpl implements SoLeaseManage{

	
	@Resource(name="soLeaseFacade")
	private SoLeaseFacade soLeaseFacade;
	


}
	