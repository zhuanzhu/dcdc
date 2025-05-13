package com.egeo.components.product.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.MerchantProductArticlesTaxReadService;


@Component
public class MerchantProductArticlesTaxFacade {
	
	@Resource
	private MerchantProductArticlesTaxReadService merchantProductArticlesTaxReadService;
	


}
	