package com.egeo.components.order.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.read.SoReminderReadService;
import com.egeo.components.order.manage.read.SoReminderReadManage;

@Service("soReminderReadService")
public class SoReminderReadServiceImpl  implements SoReminderReadService {
	@Autowired
	private SoReminderReadManage soReminderReadManage;
}
	