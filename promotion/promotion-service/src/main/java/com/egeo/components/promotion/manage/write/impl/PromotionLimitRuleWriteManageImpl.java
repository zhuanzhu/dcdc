package com.egeo.components.promotion.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.manage.write.PromotionLimitRuleWriteManage;
import com.egeo.components.promotion.dao.write.PromotionLimitRuleWriteDAO;

@Service
public class PromotionLimitRuleWriteManageImpl implements PromotionLimitRuleWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PromotionLimitRuleWriteDAO promotionLimitRuleWriteDAO;
}
	