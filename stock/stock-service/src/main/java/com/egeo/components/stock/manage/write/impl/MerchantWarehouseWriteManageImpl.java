package com.egeo.components.stock.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.manage.write.MerchantWarehouseWriteManage;
import com.egeo.components.stock.dao.write.MerchantWarehouseWriteDAO;

@Service
public class MerchantWarehouseWriteManageImpl implements MerchantWarehouseWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantWarehouseWriteDAO merchantWarehouseWriteDAO;
}
	