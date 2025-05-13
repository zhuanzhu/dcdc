package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.SoSignConfigWriteService;
import com.egeo.components.order.manage.write.SoSignConfigWriteManage;

@Service("soSignConfigWriteService")
public class SoSignConfigWriteServiceImpl  implements SoSignConfigWriteService {
	@Autowired
	private SoSignConfigWriteManage soSignConfigWriteManage;
}
	