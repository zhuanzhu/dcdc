package com.egeo.components.order.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.read.FreightTemplateReadService;
import com.egeo.components.order.manage.read.FreightTemplateReadManage;

@Service("freightTemplateReadService")
public class FreightTemplateReadServiceImpl  implements FreightTemplateReadService {
	@Autowired
	private FreightTemplateReadManage freightTemplateReadManage;
}
	