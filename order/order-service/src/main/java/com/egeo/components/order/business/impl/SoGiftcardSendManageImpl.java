package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.SoGiftcardSendManage;
import com.egeo.components.order.facade.SoGiftcardSendFacade;

@Service("soGiftcardSend")
public class SoGiftcardSendManageImpl implements SoGiftcardSendManage{

	
	@Resource(name="soGiftcardSendFacade")
	private SoGiftcardSendFacade soGiftcardSendFacade;
	


}
	