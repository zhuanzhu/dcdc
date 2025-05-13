package com.egeo.components.product.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.read.CategoryTreeNodeRelAttValueReadService;
import com.egeo.components.product.manage.read.CategoryTreeNodeRelAttValueReadManage;

@Service("categoryTreeNodeRelAttValueReadService")
public class CategoryTreeNodeRelAttValueReadServiceImpl  implements CategoryTreeNodeRelAttValueReadService {
	@Autowired
	private CategoryTreeNodeRelAttValueReadManage categoryTreeNodeRelAttValueReadManage;
}
	