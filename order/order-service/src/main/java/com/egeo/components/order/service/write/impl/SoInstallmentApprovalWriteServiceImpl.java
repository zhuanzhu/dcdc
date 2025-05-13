package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.SoInstallmentApprovalWriteService;
import com.egeo.components.order.manage.write.SoInstallmentApprovalWriteManage;

@Service("soInstallmentApprovalWriteService")
public class SoInstallmentApprovalWriteServiceImpl  implements SoInstallmentApprovalWriteService {
	@Autowired
	private SoInstallmentApprovalWriteManage soInstallmentApprovalWriteManage;
}
	