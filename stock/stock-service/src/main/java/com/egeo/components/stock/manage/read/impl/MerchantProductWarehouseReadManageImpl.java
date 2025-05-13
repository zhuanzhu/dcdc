package com.egeo.components.stock.manage.read.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.manage.read.MerchantProductWarehouseReadManage;
import com.egeo.components.stock.dao.read.MerchantProductWarehouseReadDAO;

@Service
public class MerchantProductWarehouseReadManageImpl implements MerchantProductWarehouseReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantProductWarehouseReadDAO merchantProductWarehouseReadDAO;
	
}
	