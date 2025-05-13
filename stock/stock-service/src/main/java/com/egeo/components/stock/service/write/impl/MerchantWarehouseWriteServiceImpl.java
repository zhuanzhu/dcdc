package com.egeo.components.stock.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.stock.service.write.MerchantWarehouseWriteService;
import com.egeo.components.stock.manage.write.MerchantWarehouseWriteManage;

@Service("merchantWarehouseWriteService")
public class MerchantWarehouseWriteServiceImpl  implements MerchantWarehouseWriteService {
	@Autowired
	private MerchantWarehouseWriteManage merchantWarehouseWriteManage;
}
	