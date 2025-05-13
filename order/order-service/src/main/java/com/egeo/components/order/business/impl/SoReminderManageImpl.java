package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.SoReminderManage;
import com.egeo.components.order.facade.SoReminderFacade;

@Service("soReminder")
public class SoReminderManageImpl implements SoReminderManage{

	
	@Resource(name="soReminderFacade")
	private SoReminderFacade soReminderFacade;
	


}
	