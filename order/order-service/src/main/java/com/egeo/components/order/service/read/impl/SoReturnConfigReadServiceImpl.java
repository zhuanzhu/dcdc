package com.egeo.components.order.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.read.SoReturnConfigReadService;
import com.egeo.components.order.manage.read.SoReturnConfigReadManage;

@Service("soReturnConfigReadService")
public class SoReturnConfigReadServiceImpl  implements SoReturnConfigReadService {
	@Autowired
	private SoReturnConfigReadManage soReturnConfigReadManage;
}
	