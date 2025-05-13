package com.egeo.components.product.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.read.MerchantProductArticlesTaxReadService;
import com.egeo.components.product.manage.read.MerchantProductArticlesTaxReadManage;

@Service("merchantProductArticlesTaxReadService")
public class MerchantProductArticlesTaxReadServiceImpl  implements MerchantProductArticlesTaxReadService {
	@Autowired
	private MerchantProductArticlesTaxReadManage merchantProductArticlesTaxReadManage;
}
	