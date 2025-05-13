package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.SoMessageRelWriteService;
import com.egeo.components.order.manage.write.SoMessageRelWriteManage;

@Service("soMessageRelWriteService")
public class SoMessageRelWriteServiceImpl  implements SoMessageRelWriteService {
	@Autowired
	private SoMessageRelWriteManage soMessageRelWriteManage;
}
	