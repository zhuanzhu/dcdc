package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.MerchantFreightTemplateWriteService;
import com.egeo.components.order.manage.write.MerchantFreightTemplateWriteManage;

@Service("merchantFreightTemplateWriteService")
public class MerchantFreightTemplateWriteServiceImpl  implements MerchantFreightTemplateWriteService {
	@Autowired
	private MerchantFreightTemplateWriteManage merchantFreightTemplateWriteManage;
}
	