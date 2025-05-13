package com.egeo.components.stock.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.manage.write.MerchantProductWarehouseWriteManage;
import com.egeo.components.stock.dao.write.MerchantProductWarehouseWriteDAO;

@Service
public class MerchantProductWarehouseWriteManageImpl implements MerchantProductWarehouseWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantProductWarehouseWriteDAO merchantProductWarehouseWriteDAO;
}
	