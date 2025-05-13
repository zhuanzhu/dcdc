package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.DoItemWriteService;
import com.egeo.components.order.manage.write.DoItemWriteManage;

@Service("doItemWriteService")
public class DoItemWriteServiceImpl  implements DoItemWriteService {
	@Autowired
	private DoItemWriteManage doItemWriteManage;
}
	