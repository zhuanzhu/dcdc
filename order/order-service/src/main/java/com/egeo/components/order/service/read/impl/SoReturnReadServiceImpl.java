package com.egeo.components.order.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.read.SoReturnReadService;
import com.egeo.components.order.manage.read.SoReturnReadManage;

@Service("soReturnReadService")
public class SoReturnReadServiceImpl  implements SoReturnReadService {
	@Autowired
	private SoReturnReadManage soReturnReadManage;
}
	