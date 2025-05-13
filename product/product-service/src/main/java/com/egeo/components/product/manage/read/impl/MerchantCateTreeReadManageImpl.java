package com.egeo.components.product.manage.read.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.MerchantCateTreeReadManage;
import com.egeo.components.product.dao.read.MerchantCateTreeReadDAO;

@Service
public class MerchantCateTreeReadManageImpl implements MerchantCateTreeReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantCateTreeReadDAO merchantCateTreeReadDAO;
	
}
	