package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.FreightTemplateItemWriteService;
import com.egeo.components.order.manage.write.FreightTemplateItemWriteManage;

@Service("freightTemplateItemWriteService")
public class FreightTemplateItemWriteServiceImpl  implements FreightTemplateItemWriteService {
	@Autowired
	private FreightTemplateItemWriteManage freightTemplateItemWriteManage;
}
	