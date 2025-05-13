package com.egeo.components.order.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.read.ExpressMapConfigReadService;
import com.egeo.components.order.manage.read.ExpressMapConfigReadManage;

@Service("expressMapConfigReadService")
public class ExpressMapConfigReadServiceImpl  implements ExpressMapConfigReadService {
	@Autowired
	private ExpressMapConfigReadManage expressMapConfigReadManage;
}
	