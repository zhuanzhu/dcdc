package com.egeo.components.order.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.read.ContractItemReadService;
import com.egeo.components.order.manage.read.ContractItemReadManage;

@Service("contractItemReadService")
public class ContractItemReadServiceImpl  implements ContractItemReadService {
	@Autowired
	private ContractItemReadManage contractItemReadManage;
}
	