package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.write.ProductCateTreeNodeWriteService;
import com.egeo.components.product.manage.write.ProductCateTreeNodeWriteManage;


@Service("productCateTreeNodeWriteService")
public class ProductCateTreeNodeWriteServiceImpl  implements ProductCateTreeNodeWriteService {
	@Autowired
	private ProductCateTreeNodeWriteManage productCateTreeNodeWriteManage;
}
	