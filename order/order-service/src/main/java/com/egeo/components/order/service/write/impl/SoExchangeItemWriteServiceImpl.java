package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.SoExchangeItemWriteService;
import com.egeo.components.order.manage.write.SoExchangeItemWriteManage;

@Service("soExchangeItemWriteService")
public class SoExchangeItemWriteServiceImpl  implements SoExchangeItemWriteService {
	@Autowired
	private SoExchangeItemWriteManage soExchangeItemWriteManage;
}
	