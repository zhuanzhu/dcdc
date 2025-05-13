package com.egeo.components.order.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.read.SoInstallmentApprovalReadService;
import com.egeo.components.order.manage.read.SoInstallmentApprovalReadManage;

@Service("soInstallmentApprovalReadService")
public class SoInstallmentApprovalReadServiceImpl  implements SoInstallmentApprovalReadService {
	@Autowired
	private SoInstallmentApprovalReadManage soInstallmentApprovalReadManage;
}
	