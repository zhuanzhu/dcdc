package com.egeo.components.order.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.read.SoModifyPriceReadService;
import com.egeo.components.order.manage.read.SoModifyPriceReadManage;

@Service("soModifyPriceReadService")
public class SoModifyPriceReadServiceImpl  implements SoModifyPriceReadService {
	@Autowired
	private SoModifyPriceReadManage soModifyPriceReadManage;
}
	