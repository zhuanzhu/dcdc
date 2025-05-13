package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.ProductVideoWriteService;
import com.egeo.components.product.manage.write.ProductVideoWriteManage;

@Service("productVideoWriteService")
public class ProductVideoWriteServiceImpl  implements ProductVideoWriteService {
	@Autowired
	private ProductVideoWriteManage productVideoWriteManage;
}
	