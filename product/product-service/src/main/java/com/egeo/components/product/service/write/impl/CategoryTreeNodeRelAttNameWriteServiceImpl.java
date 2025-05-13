package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.CategoryTreeNodeRelAttNameWriteService;
import com.egeo.components.product.manage.write.CategoryTreeNodeRelAttNameWriteManage;

@Service("categoryTreeNodeRelAttNameWriteService")
public class CategoryTreeNodeRelAttNameWriteServiceImpl  implements CategoryTreeNodeRelAttNameWriteService {
	@Autowired
	private CategoryTreeNodeRelAttNameWriteManage categoryTreeNodeRelAttNameWriteManage;
}
	