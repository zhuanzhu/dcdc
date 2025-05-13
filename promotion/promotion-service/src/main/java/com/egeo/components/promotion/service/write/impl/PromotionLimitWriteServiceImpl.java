package com.egeo.components.promotion.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.write.PromotionLimitWriteService;
import com.egeo.components.promotion.manage.write.PromotionLimitWriteManage;

@Service("promotionLimitWriteService")
public class PromotionLimitWriteServiceImpl implements PromotionLimitWriteService {
	@Autowired
	private PromotionLimitWriteManage promotionLimitWriteManage;
}
	