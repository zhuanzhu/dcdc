package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.SoModifyPriceWriteService;
import com.egeo.components.order.manage.write.SoModifyPriceWriteManage;

@Service("soModifyPriceWriteService")
public class SoModifyPriceWriteServiceImpl  implements SoModifyPriceWriteService {
	@Autowired
	private SoModifyPriceWriteManage soModifyPriceWriteManage;
}
	