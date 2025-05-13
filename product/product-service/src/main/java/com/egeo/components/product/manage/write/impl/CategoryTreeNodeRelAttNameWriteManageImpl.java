package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.CategoryTreeNodeRelAttNameWriteManage;
import com.egeo.components.product.dao.write.CategoryTreeNodeRelAttNameWriteDAO;

@Service
public class CategoryTreeNodeRelAttNameWriteManageImpl implements CategoryTreeNodeRelAttNameWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CategoryTreeNodeRelAttNameWriteDAO categoryTreeNodeRelAttNameWriteDAO;
}
	