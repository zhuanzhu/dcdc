package com.egeo.components.product.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.read.MerchantCateTreeReadService;
import com.egeo.components.product.manage.read.MerchantCateTreeReadManage;

@Service("merchantCateTreeReadService")
public class MerchantCateTreeReadServiceImpl  implements MerchantCateTreeReadService {
	@Autowired
	private MerchantCateTreeReadManage merchantCateTreeReadManage;
}
	