package com.egeo.components.promotion.manage.read.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.manage.read.PromotionMerchantReadManage;
import com.egeo.components.promotion.dao.read.PromotionMerchantReadDAO;

@Service
public class PromotionMerchantReadManageImpl implements PromotionMerchantReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PromotionMerchantReadDAO promotionMerchantReadDAO;
	
}
	