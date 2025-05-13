package com.egeo.components.product.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.CategoryTreeNodeRelAttNameReadService;


@Component
public class CategoryTreeNodeRelAttNameFacade {
	
	@Resource
	private CategoryTreeNodeRelAttNameReadService categoryTreeNodeRelAttNameReadService;
	


}
	