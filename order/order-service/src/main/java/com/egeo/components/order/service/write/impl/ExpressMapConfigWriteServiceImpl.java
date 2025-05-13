package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.ExpressMapConfigWriteService;
import com.egeo.components.order.manage.write.ExpressMapConfigWriteManage;

@Service("expressMapConfigWriteService")
public class ExpressMapConfigWriteServiceImpl  implements ExpressMapConfigWriteService {
	@Autowired
	private ExpressMapConfigWriteManage expressMapConfigWriteManage;
}
	