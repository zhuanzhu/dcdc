package com.egeo.components.order.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.read.DoItemReadService;
import com.egeo.components.order.manage.read.DoItemReadManage;

@Service("doItemReadService")
public class DoItemReadServiceImpl  implements DoItemReadService {
	@Autowired
	private DoItemReadManage doItemReadManage;
}
	