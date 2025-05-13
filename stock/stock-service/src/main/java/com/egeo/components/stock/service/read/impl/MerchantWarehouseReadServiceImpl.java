package com.egeo.components.stock.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.stock.service.read.MerchantWarehouseReadService;
import com.egeo.components.stock.manage.read.MerchantWarehouseReadManage;

@Service("merchantWarehouseReadService")
public class MerchantWarehouseReadServiceImpl  implements MerchantWarehouseReadService {
	@Autowired
	private MerchantWarehouseReadManage merchantWarehouseReadManage;
}
	