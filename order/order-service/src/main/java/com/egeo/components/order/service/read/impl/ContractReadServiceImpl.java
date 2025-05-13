package com.egeo.components.order.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.read.ContractReadService;
import com.egeo.components.order.manage.read.ContractReadManage;

@Service("contractReadService")
public class ContractReadServiceImpl  implements ContractReadService {
	@Autowired
	private ContractReadManage contractReadManage;
}
	