package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.CategoryTreeNodeRelAttValueWriteManage;
import com.egeo.components.product.dao.write.CategoryTreeNodeRelAttValueWriteDAO;

@Service
public class CategoryTreeNodeRelAttValueWriteManageImpl implements CategoryTreeNodeRelAttValueWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CategoryTreeNodeRelAttValueWriteDAO categoryTreeNodeRelAttValueWriteDAO;
}
	