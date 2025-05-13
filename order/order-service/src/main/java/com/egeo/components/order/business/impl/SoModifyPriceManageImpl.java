package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.SoModifyPriceManage;
import com.egeo.components.order.facade.SoModifyPriceFacade;

@Service("soModifyPrice")
public class SoModifyPriceManageImpl implements SoModifyPriceManage{

	
	@Resource(name="soModifyPriceFacade")
	private SoModifyPriceFacade soModifyPriceFacade;
	


}
	