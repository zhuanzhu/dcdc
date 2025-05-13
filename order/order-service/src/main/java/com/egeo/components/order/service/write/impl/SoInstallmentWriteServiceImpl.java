package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.SoInstallmentWriteService;
import com.egeo.components.order.manage.write.SoInstallmentWriteManage;

@Service("soInstallmentWriteService")
public class SoInstallmentWriteServiceImpl  implements SoInstallmentWriteService {
	@Autowired
	private SoInstallmentWriteManage soInstallmentWriteManage;
}
	