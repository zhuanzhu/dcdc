package com.egeo.components.promotion.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.manage.write.PromotionLimitWriteManage;
import com.egeo.components.promotion.dao.write.PromotionLimitWriteDAO;

@Service
public class PromotionLimitWriteManageImpl implements PromotionLimitWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PromotionLimitWriteDAO promotionLimitWriteDAO;
}
	