package com.egeo.components.order.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.read.CategoryReadService;
import com.egeo.components.order.manage.read.CategoryReadManage;

@Service("categoryReadService")
public class CategoryReadServiceImpl  implements CategoryReadService {
	@Autowired
	private CategoryReadManage categoryReadManage;
}
	