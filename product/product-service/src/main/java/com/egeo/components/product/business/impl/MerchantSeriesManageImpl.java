package com.egeo.components.product.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.MerchantSeriesManage;
import com.egeo.components.product.facade.MerchantSeriesFacade;

@Service("merchantSeries")
public class MerchantSeriesManageImpl implements MerchantSeriesManage{

	
	@Resource(name="merchantSeriesFacade")
	private MerchantSeriesFacade merchantSeriesFacade;
	


}
	