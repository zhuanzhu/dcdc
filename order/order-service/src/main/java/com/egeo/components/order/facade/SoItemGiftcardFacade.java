package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.SoItemGiftcardReadService;
import com.egeo.components.order.service.write.SoItemGiftcardWriteService;


@Component
public class SoItemGiftcardFacade {
	
	@Resource
	private SoItemGiftcardReadService soItemGiftcardReadService;
	
	@Resource
	private SoItemGiftcardWriteService soItemGiftcardWriteService;
	


}
	