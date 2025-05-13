package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.DoWriteService;
import com.egeo.components.order.manage.write.DoWriteManage;

@Service("doWriteService")
public class DoWriteServiceImpl  implements DoWriteService {
	@Autowired
	private DoWriteManage doWriteManage;
}
	