package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.SoReturnConfigWriteService;
import com.egeo.components.order.manage.write.SoReturnConfigWriteManage;

@Service("soReturnConfigWriteService")
public class SoReturnConfigWriteServiceImpl  implements SoReturnConfigWriteService {
	@Autowired
	private SoReturnConfigWriteManage soReturnConfigWriteManage;
}
	