package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.MerchantCateTreeNodeWriteService;
import com.egeo.components.product.manage.write.MerchantCateTreeNodeWriteManage;

@Service("merchantCateTreeNodeWriteService")
public class MerchantCateTreeNodeWriteServiceImpl  implements MerchantCateTreeNodeWriteService {
	@Autowired
	private MerchantCateTreeNodeWriteManage merchantCateTreeNodeWriteManage;
}
	