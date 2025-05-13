package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.SoGiftcardSendReadService;
import com.egeo.components.order.service.write.SoGiftcardSendWriteService;


@Component
public class SoGiftcardSendFacade {
	
	@Resource
	private SoGiftcardSendReadService soGiftcardSendReadService;
	
	@Resource
	private SoGiftcardSendWriteService soGiftcardSendWriteService;
	


}
	