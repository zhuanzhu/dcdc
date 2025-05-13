package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.ContractItemManage;
import com.egeo.components.order.facade.ContractItemFacade;

@Service("contractItem")
public class ContractItemManageImpl implements ContractItemManage{

	
	@Resource(name="contractItemFacade")
	private ContractItemFacade contractItemFacade;
	


}
	