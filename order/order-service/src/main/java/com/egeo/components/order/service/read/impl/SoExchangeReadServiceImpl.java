package com.egeo.components.order.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.read.SoExchangeReadService;
import com.egeo.components.order.manage.read.SoExchangeReadManage;

@Service("soExchangeReadService")
public class SoExchangeReadServiceImpl  implements SoExchangeReadService {
	@Autowired
	private SoExchangeReadManage soExchangeReadManage;
}
	