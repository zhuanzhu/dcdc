package com.egeo.components.order.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.read.DoReadService;
import com.egeo.components.order.manage.read.DoReadManage;

@Service("doReadService")
public class DoReadServiceImpl  implements DoReadService {
	@Autowired
	private DoReadManage doReadManage;
}
	