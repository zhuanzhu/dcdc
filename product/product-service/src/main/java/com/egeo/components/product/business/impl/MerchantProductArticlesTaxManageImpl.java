package com.egeo.components.product.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.MerchantProductArticlesTaxManage;
import com.egeo.components.product.facade.MerchantProductArticlesTaxFacade;

@Service("merchantProductArticlesTax")
public class MerchantProductArticlesTaxManageImpl implements MerchantProductArticlesTaxManage{

	
	@Resource(name="merchantProductArticlesTaxFacade")
	private MerchantProductArticlesTaxFacade merchantProductArticlesTaxFacade;
	


}
	