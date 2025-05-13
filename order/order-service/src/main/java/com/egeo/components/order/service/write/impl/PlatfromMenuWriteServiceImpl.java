package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.PlatfromMenuWriteService;
import com.egeo.components.order.manage.write.PlatfromMenuWriteManage;

@Service("platfromMenuWriteService")
public class PlatfromMenuWriteServiceImpl  implements PlatfromMenuWriteService {
	@Autowired
	private PlatfromMenuWriteManage platfromMenuWriteManage;
}
	