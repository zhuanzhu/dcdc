package com.egeo.components.order.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.read.SoProcessConfigReadService;
import com.egeo.components.order.manage.read.SoProcessConfigReadManage;

@Service("soProcessConfigReadService")
public class SoProcessConfigReadServiceImpl  implements SoProcessConfigReadService {
	@Autowired
	private SoProcessConfigReadManage soProcessConfigReadManage;
}
	