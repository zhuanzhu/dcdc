package com.egeo.components.product.manage.read.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.MerchantVideoReadManage;
import com.egeo.components.product.dao.read.MerchantVideoReadDAO;

@Service
public class MerchantVideoReadManageImpl implements MerchantVideoReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantVideoReadDAO merchantVideoReadDAO;
	
}
	