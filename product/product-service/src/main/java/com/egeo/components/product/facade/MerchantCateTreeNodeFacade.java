package com.egeo.components.product.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.MerchantCateTreeNodeReadService;


@Component
public class MerchantCateTreeNodeFacade {
	
	@Resource
	private MerchantCateTreeNodeReadService merchantCateTreeNodeReadService;
	


}
	