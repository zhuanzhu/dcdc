package com.egeo.components.stock.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.stock.service.write.WmsWarehouseWriteService;
import com.egeo.components.stock.manage.write.WmsWarehouseWriteManage;

@Service("wmsWarehouseWriteService")
public class WmsWarehouseWriteServiceImpl  implements WmsWarehouseWriteService {
	@Autowired
	private WmsWarehouseWriteManage wmsWarehouseWriteManage;
}
	