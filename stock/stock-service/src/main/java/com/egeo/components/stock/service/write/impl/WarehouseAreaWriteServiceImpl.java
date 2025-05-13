package com.egeo.components.stock.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.stock.service.write.WarehouseAreaWriteService;
import com.egeo.components.stock.manage.write.WarehouseAreaWriteManage;

@Service("warehouseAreaWriteService")
public class WarehouseAreaWriteServiceImpl  implements WarehouseAreaWriteService {
	@Autowired
	private WarehouseAreaWriteManage warehouseAreaWriteManage;
}
	