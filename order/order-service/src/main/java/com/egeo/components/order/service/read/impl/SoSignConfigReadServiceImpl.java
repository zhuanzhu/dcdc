package com.egeo.components.order.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.read.SoSignConfigReadService;
import com.egeo.components.order.manage.read.SoSignConfigReadManage;

@Service("soSignConfigReadService")
public class SoSignConfigReadServiceImpl  implements SoSignConfigReadService {
	@Autowired
	private SoSignConfigReadManage soSignConfigReadManage;
}
	