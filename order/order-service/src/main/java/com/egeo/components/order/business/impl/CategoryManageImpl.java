package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.CategoryManage;
import com.egeo.components.order.facade.CategoryFacade;

@Service("category")
public class CategoryManageImpl implements CategoryManage{

	
	@Resource(name="categoryFacade")
	private CategoryFacade categoryFacade;
	


}
	