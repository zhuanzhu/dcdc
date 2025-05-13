package com.egeo.components.stock.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.business.MerchantAreaManage;
import com.egeo.components.stock.facade.MerchantAreaFacade;

@Service("merchantArea")
public class MerchantAreaManageImpl implements MerchantAreaManage{

	
	@Resource(name="merchantAreaFacade")
	private MerchantAreaFacade merchantAreaFacade;
	


}
	