package com.egeo.components.product.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.CategoryTreeNodeRelAttValueManage;
import com.egeo.components.product.facade.CategoryTreeNodeRelAttValueFacade;

@Service("categoryTreeNodeRelAttValue")
public class CategoryTreeNodeRelAttValueManageImpl implements CategoryTreeNodeRelAttValueManage{

	
	@Resource(name="categoryTreeNodeRelAttValueFacade")
	private CategoryTreeNodeRelAttValueFacade categoryTreeNodeRelAttValueFacade;
	


}
	