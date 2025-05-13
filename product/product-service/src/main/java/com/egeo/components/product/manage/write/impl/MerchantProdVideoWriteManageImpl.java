package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.MerchantProdVideoWriteManage;
import com.egeo.components.product.dao.write.MerchantProdVideoWriteDAO;

@Service
public class MerchantProdVideoWriteManageImpl implements MerchantProdVideoWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantProdVideoWriteDAO merchantProdVideoWriteDAO;
}
	