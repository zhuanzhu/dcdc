package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.ExpressConfigWriteService;
import com.egeo.components.order.manage.write.ExpressConfigWriteManage;

@Service("expressConfigWriteService")
public class ExpressConfigWriteServiceImpl  implements ExpressConfigWriteService {
	@Autowired
	private ExpressConfigWriteManage expressConfigWriteManage;
}
	