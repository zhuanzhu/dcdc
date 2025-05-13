package com.egeo.components.stock.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.stock.service.read.MerchantProductWarehouseReadService;
import com.egeo.components.stock.manage.read.MerchantProductWarehouseReadManage;

@Service("merchantProductWarehouseReadService")
public class MerchantProductWarehouseReadServiceImpl  implements MerchantProductWarehouseReadService {
	@Autowired
	private MerchantProductWarehouseReadManage merchantProductWarehouseReadManage;
}
	