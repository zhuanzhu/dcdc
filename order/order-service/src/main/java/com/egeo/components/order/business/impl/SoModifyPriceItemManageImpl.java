package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.SoModifyPriceItemManage;
import com.egeo.components.order.facade.SoModifyPriceItemFacade;

@Service("soModifyPriceItem")
public class SoModifyPriceItemManageImpl implements SoModifyPriceItemManage{

	
	@Resource(name="soModifyPriceItemFacade")
	private SoModifyPriceItemFacade soModifyPriceItemFacade;
	


}
	