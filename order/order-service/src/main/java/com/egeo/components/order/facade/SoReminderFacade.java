package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.SoReminderReadService;
import com.egeo.components.order.service.write.SoReminderWriteService;


@Component
public class SoReminderFacade {
	
	@Resource
	private SoReminderReadService soReminderReadService;
	
	@Resource
	private SoReminderWriteService soReminderWriteService;
	


}
	