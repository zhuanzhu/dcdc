package com.egeo.components.product.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.read.ProductVideoReadService;
import com.egeo.components.product.manage.read.ProductVideoReadManage;

@Service("productVideoReadService")
public class ProductVideoReadServiceImpl  implements ProductVideoReadService {
	@Autowired
	private ProductVideoReadManage productVideoReadManage;
}
	