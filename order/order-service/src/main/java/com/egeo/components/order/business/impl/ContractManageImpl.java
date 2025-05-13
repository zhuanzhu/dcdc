package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.ContractManage;
import com.egeo.components.order.facade.ContractFacade;

@Service("contract")
public class ContractManageImpl implements ContractManage{

	
	@Resource(name="contractFacade")
	private ContractFacade contractFacade;
	


}
	