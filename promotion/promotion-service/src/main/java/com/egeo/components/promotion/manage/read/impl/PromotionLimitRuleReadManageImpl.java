package com.egeo.components.promotion.manage.read.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.manage.read.PromotionLimitRuleReadManage;
import com.egeo.components.promotion.dao.read.PromotionLimitRuleReadDAO;

@Service
public class PromotionLimitRuleReadManageImpl implements PromotionLimitRuleReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PromotionLimitRuleReadDAO promotionLimitRuleReadDAO;
	
}
	