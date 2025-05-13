package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.SoStatusCompensationWriteService;
import com.egeo.components.order.manage.write.SoStatusCompensationWriteManage;

@Service("soStatusCompensationWriteService")
public class SoStatusCompensationWriteServiceImpl  implements SoStatusCompensationWriteService {
	@Autowired
	private SoStatusCompensationWriteManage soStatusCompensationWriteManage;
}
	