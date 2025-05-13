package com.egeo.components.product.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.read.MerchantCateTreeNodeReadService;
import com.egeo.components.product.manage.read.MerchantCateTreeNodeReadManage;

@Service("merchantCateTreeNodeReadService")
public class MerchantCateTreeNodeReadServiceImpl  implements MerchantCateTreeNodeReadService {
	@Autowired
	private MerchantCateTreeNodeReadManage merchantCateTreeNodeReadManage;
}
	