package com.egeo.components.order.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.read.SoErrorLogReadService;
import com.egeo.components.order.manage.read.SoErrorLogReadManage;

@Service("soErrorLogReadService")
public class SoErrorLogReadServiceImpl  implements SoErrorLogReadService {
	@Autowired
	private SoErrorLogReadManage soErrorLogReadManage;
}
	