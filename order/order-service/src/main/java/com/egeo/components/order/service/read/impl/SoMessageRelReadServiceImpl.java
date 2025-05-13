package com.egeo.components.order.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.read.SoMessageRelReadService;
import com.egeo.components.order.manage.read.SoMessageRelReadManage;

@Service("soMessageRelReadService")
public class SoMessageRelReadServiceImpl  implements SoMessageRelReadService {
	@Autowired
	private SoMessageRelReadManage soMessageRelReadManage;
}
	