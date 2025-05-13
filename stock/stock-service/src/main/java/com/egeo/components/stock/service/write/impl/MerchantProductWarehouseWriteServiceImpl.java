package com.egeo.components.stock.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.stock.service.write.MerchantProductWarehouseWriteService;
import com.egeo.components.stock.manage.write.MerchantProductWarehouseWriteManage;

@Service("merchantProductWarehouseWriteService")
public class MerchantProductWarehouseWriteServiceImpl  implements MerchantProductWarehouseWriteService {
	@Autowired
	private MerchantProductWarehouseWriteManage merchantProductWarehouseWriteManage;
}
	