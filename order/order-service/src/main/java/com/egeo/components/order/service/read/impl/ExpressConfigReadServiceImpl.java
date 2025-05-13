package com.egeo.components.order.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.read.ExpressConfigReadService;
import com.egeo.components.order.manage.read.ExpressConfigReadManage;

@Service("expressConfigReadService")
public class ExpressConfigReadServiceImpl  implements ExpressConfigReadService {
	@Autowired
	private ExpressConfigReadManage expressConfigReadManage;
}
	