package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.SoItemGiftcardManage;
import com.egeo.components.order.facade.SoItemGiftcardFacade;

@Service("soItemGiftcard")
public class SoItemGiftcardManageImpl implements SoItemGiftcardManage{

	
	@Resource(name="soItemGiftcardFacade")
	private SoItemGiftcardFacade soItemGiftcardFacade;
	


}
	