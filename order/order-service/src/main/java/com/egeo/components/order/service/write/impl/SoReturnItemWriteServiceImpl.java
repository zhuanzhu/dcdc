package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.SoReturnItemWriteService;
import com.egeo.components.order.manage.write.SoReturnItemWriteManage;

@Service("soReturnItemWriteService")
public class SoReturnItemWriteServiceImpl  implements SoReturnItemWriteService {
	@Autowired
	private SoReturnItemWriteManage soReturnItemWriteManage;
}
	