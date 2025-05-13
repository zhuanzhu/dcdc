package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.SoLeaseWriteService;
import com.egeo.components.order.manage.write.SoLeaseWriteManage;

@Service("soLeaseWriteService")
public class SoLeaseWriteServiceImpl  implements SoLeaseWriteService {
	@Autowired
	private SoLeaseWriteManage soLeaseWriteManage;
}
	