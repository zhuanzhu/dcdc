package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.SoReminderWriteService;
import com.egeo.components.order.manage.write.SoReminderWriteManage;

@Service("soReminderWriteService")
public class SoReminderWriteServiceImpl  implements SoReminderWriteService {
	@Autowired
	private SoReminderWriteManage soReminderWriteManage;
}
	