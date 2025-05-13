package com.egeo.components.product.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.ProductVideoReadService;


@Component
public class ProductVideoFacade {
	
	@Resource
	private ProductVideoReadService productVideoReadService;
	


}
	