package com.egeo.components.order.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.read.SoReturnItemReadService;
import com.egeo.components.order.manage.read.SoReturnItemReadManage;

@Service("soReturnItemReadService")
public class SoReturnItemReadServiceImpl  implements SoReturnItemReadService {
	@Autowired
	private SoReturnItemReadManage soReturnItemReadManage;
}
	