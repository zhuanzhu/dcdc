package com.egeo.components.product.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.MerchantProductPromotionManage;
import com.egeo.components.product.facade.MerchantProductPromotionFacade;

@Service("merchantProductPromotion")
public class MerchantProductPromotionManageImpl implements MerchantProductPromotionManage{

	
	@Resource(name="merchantProductPromotionFacade")
	private MerchantProductPromotionFacade merchantProductPromotionFacade;
	


}
	