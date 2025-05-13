package com.egeo.components.promotion.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.manage.write.PromotionWriteManage;
import com.egeo.components.promotion.dao.write.PromotionWriteDAO;

@Service
public class PromotionWriteManageImpl implements PromotionWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PromotionWriteDAO promotionWriteDAO;
}
	