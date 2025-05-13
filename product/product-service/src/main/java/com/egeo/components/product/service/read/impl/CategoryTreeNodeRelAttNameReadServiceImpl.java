package com.egeo.components.product.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.read.CategoryTreeNodeRelAttNameReadService;
import com.egeo.components.product.manage.read.CategoryTreeNodeRelAttNameReadManage;

@Service("categoryTreeNodeRelAttNameReadService")
public class CategoryTreeNodeRelAttNameReadServiceImpl  implements CategoryTreeNodeRelAttNameReadService {
	@Autowired
	private CategoryTreeNodeRelAttNameReadManage categoryTreeNodeRelAttNameReadManage;
}
	