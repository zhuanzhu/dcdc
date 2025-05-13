package com.egeo.components.promotion.manage.read.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.manage.read.PromotionLimitReadManage;
import com.egeo.components.promotion.dao.read.PromotionLimitReadDAO;

@Service
public class PromotionLimitReadManageImpl implements PromotionLimitReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PromotionLimitReadDAO promotionLimitReadDAO;
	
}
	