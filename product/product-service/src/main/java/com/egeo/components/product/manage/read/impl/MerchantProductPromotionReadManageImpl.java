package com.egeo.components.product.manage.read.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.MerchantProductPromotionReadManage;
import com.egeo.components.product.dao.read.MerchantProductPromotionReadDAO;

@Service
public class MerchantProductPromotionReadManageImpl implements MerchantProductPromotionReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantProductPromotionReadDAO merchantProductPromotionReadDAO;
	
}
	