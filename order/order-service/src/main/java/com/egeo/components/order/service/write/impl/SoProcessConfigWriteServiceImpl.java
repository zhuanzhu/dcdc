package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.SoProcessConfigWriteService;
import com.egeo.components.order.manage.write.SoProcessConfigWriteManage;

@Service("soProcessConfigWriteService")
public class SoProcessConfigWriteServiceImpl  implements SoProcessConfigWriteService {
	@Autowired
	private SoProcessConfigWriteManage soProcessConfigWriteManage;
}
	