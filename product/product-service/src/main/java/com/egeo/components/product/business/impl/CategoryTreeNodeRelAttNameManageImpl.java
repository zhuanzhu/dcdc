package com.egeo.components.product.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.CategoryTreeNodeRelAttNameManage;
import com.egeo.components.product.facade.CategoryTreeNodeRelAttNameFacade;

@Service("categoryTreeNodeRelAttName")
public class CategoryTreeNodeRelAttNameManageImpl implements CategoryTreeNodeRelAttNameManage{

	
	@Resource(name="categoryTreeNodeRelAttNameFacade")
	private CategoryTreeNodeRelAttNameFacade categoryTreeNodeRelAttNameFacade;
	


}
	