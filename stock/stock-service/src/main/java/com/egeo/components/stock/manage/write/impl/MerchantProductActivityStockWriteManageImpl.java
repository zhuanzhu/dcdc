package com.egeo.components.stock.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.manage.write.MerchantProductActivityStockWriteManage;
import com.egeo.components.stock.dao.write.MerchantProductActivityStockWriteDAO;

@Service
public class MerchantProductActivityStockWriteManageImpl implements MerchantProductActivityStockWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantProductActivityStockWriteDAO merchantProductActivityStockWriteDAO;
}
	