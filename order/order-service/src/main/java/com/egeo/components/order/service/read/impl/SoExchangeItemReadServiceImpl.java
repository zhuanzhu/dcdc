package com.egeo.components.order.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.read.SoExchangeItemReadService;
import com.egeo.components.order.manage.read.SoExchangeItemReadManage;

@Service("soExchangeItemReadService")
public class SoExchangeItemReadServiceImpl  implements SoExchangeItemReadService {
	@Autowired
	private SoExchangeItemReadManage soExchangeItemReadManage;
}
	