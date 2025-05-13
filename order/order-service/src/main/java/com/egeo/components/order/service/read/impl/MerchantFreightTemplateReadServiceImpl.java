package com.egeo.components.order.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.read.MerchantFreightTemplateReadService;
import com.egeo.components.order.manage.read.MerchantFreightTemplateReadManage;

@Service("merchantFreightTemplateReadService")
public class MerchantFreightTemplateReadServiceImpl  implements MerchantFreightTemplateReadService {
	@Autowired
	private MerchantFreightTemplateReadManage merchantFreightTemplateReadManage;
}
	