package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.ContractWriteService;
import com.egeo.components.order.manage.write.ContractWriteManage;

@Service("contractWriteService")
public class ContractWriteServiceImpl  implements ContractWriteService {
	@Autowired
	private ContractWriteManage contractWriteManage;
}
	