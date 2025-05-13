package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.SoErrorLogWriteService;
import com.egeo.components.order.manage.write.SoErrorLogWriteManage;

@Service("soErrorLogWriteService")
public class SoErrorLogWriteServiceImpl  implements SoErrorLogWriteService {
	@Autowired
	private SoErrorLogWriteManage soErrorLogWriteManage;
}
	