package com.egeo.components.product.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.MerchantSeriesProductAttManage;
import com.egeo.components.product.facade.MerchantSeriesProductAttFacade;

@Service("merchantSeriesProductAtt")
public class MerchantSeriesProductAttManageImpl implements MerchantSeriesProductAttManage{

	
	@Resource(name="merchantSeriesProductAttFacade")
	private MerchantSeriesProductAttFacade merchantSeriesProductAttFacade;
	


}
	