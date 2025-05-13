package com.egeo.components.product.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.CategoryTreeNodeRelAttValueReadService;


@Component
public class CategoryTreeNodeRelAttValueFacade {
	
	@Resource
	private CategoryTreeNodeRelAttValueReadService categoryTreeNodeRelAttValueReadService;
	


}
	