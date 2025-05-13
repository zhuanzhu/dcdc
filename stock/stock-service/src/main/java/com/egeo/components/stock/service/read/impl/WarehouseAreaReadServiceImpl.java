package com.egeo.components.stock.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.stock.service.read.WarehouseAreaReadService;
import com.egeo.components.stock.manage.read.WarehouseAreaReadManage;

@Service("warehouseAreaReadService")
public class WarehouseAreaReadServiceImpl  implements WarehouseAreaReadService {
	@Autowired
	private WarehouseAreaReadManage warehouseAreaReadManage;
}
	