package com.egeo.components.order.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.read.FreightTemplateItemReadService;
import com.egeo.components.order.manage.read.FreightTemplateItemReadManage;

@Service("freightTemplateItemReadService")
public class FreightTemplateItemReadServiceImpl  implements FreightTemplateItemReadService {
	@Autowired
	private FreightTemplateItemReadManage freightTemplateItemReadManage;
}
	