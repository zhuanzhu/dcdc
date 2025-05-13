package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.MerchantProductArticlesTaxWriteService;
import com.egeo.components.product.manage.write.MerchantProductArticlesTaxWriteManage;

@Service("merchantProductArticlesTaxWriteService")
public class MerchantProductArticlesTaxWriteServiceImpl  implements MerchantProductArticlesTaxWriteService {
	@Autowired
	private MerchantProductArticlesTaxWriteManage merchantProductArticlesTaxWriteManage;
}
	