package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.CategoryTreeNodeRelAttValueWriteService;
import com.egeo.components.product.manage.write.CategoryTreeNodeRelAttValueWriteManage;

@Service("categoryTreeNodeRelAttValueWriteService")
public class CategoryTreeNodeRelAttValueWriteServiceImpl  implements CategoryTreeNodeRelAttValueWriteService {
	@Autowired
	private CategoryTreeNodeRelAttValueWriteManage categoryTreeNodeRelAttValueWriteManage;
}
	