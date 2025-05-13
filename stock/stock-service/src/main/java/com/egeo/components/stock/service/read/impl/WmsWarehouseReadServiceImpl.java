package com.egeo.components.stock.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.stock.service.read.WmsWarehouseReadService;
import com.egeo.components.stock.manage.read.WmsWarehouseReadManage;

@Service("wmsWarehouseReadService")
public class WmsWarehouseReadServiceImpl  implements WmsWarehouseReadService {
	@Autowired
	private WmsWarehouseReadManage wmsWarehouseReadManage;
}
	