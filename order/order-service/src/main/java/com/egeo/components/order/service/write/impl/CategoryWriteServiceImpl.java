package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.CategoryWriteService;
import com.egeo.components.order.manage.write.CategoryWriteManage;

@Service("categoryWriteService")
public class CategoryWriteServiceImpl  implements CategoryWriteService {
	@Autowired
	private CategoryWriteManage categoryWriteManage;
}
	