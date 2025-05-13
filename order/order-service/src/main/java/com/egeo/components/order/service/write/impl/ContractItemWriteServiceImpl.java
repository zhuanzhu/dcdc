package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.ContractItemWriteService;
import com.egeo.components.order.manage.write.ContractItemWriteManage;

@Service("contractItemWriteService")
public class ContractItemWriteServiceImpl  implements ContractItemWriteService {
	@Autowired
	private ContractItemWriteManage contractItemWriteManage;
}
	