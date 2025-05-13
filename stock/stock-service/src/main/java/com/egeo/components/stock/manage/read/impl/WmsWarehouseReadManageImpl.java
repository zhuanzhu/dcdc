package com.egeo.components.stock.manage.read.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.manage.read.WmsWarehouseReadManage;
import com.egeo.components.stock.dao.read.WmsWarehouseReadDAO;

@Service
public class WmsWarehouseReadManageImpl implements WmsWarehouseReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private WmsWarehouseReadDAO wmsWarehouseReadDAO;
	
}
	