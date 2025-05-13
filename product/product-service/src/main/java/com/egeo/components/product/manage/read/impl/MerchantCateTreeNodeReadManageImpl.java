package com.egeo.components.product.manage.read.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.MerchantCateTreeNodeReadManage;
import com.egeo.components.product.dao.read.MerchantCateTreeNodeReadDAO;

@Service
public class MerchantCateTreeNodeReadManageImpl implements MerchantCateTreeNodeReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantCateTreeNodeReadDAO merchantCateTreeNodeReadDAO;
	
}
	