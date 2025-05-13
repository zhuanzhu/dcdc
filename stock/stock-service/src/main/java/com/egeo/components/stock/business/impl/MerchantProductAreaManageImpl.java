package com.egeo.components.stock.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.business.MerchantProductAreaManage;
import com.egeo.components.stock.facade.MerchantProductAreaFacade;

@Service("merchantProductArea")
public class MerchantProductAreaManageImpl implements MerchantProductAreaManage{

	
	@Resource(name="merchantProductAreaFacade")
	private MerchantProductAreaFacade merchantProductAreaFacade;
	


}
	