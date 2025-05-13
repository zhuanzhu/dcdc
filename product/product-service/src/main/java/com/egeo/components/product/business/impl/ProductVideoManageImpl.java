package com.egeo.components.product.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.ProductVideoManage;
import com.egeo.components.product.facade.ProductVideoFacade;

@Service("productVideo")
public class ProductVideoManageImpl implements ProductVideoManage{

	
	@Resource(name="productVideoFacade")
	private ProductVideoFacade productVideoFacade;
	


}
	