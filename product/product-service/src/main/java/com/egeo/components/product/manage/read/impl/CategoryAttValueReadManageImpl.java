package com.egeo.components.product.manage.read.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.CategoryAttValueReadManage;
import com.egeo.components.product.dao.read.CategoryAttValueReadDAO;
import com.egeo.components.product.po.CategoryAttValuePO;

@Service
public class CategoryAttValueReadManageImpl implements CategoryAttValueReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CategoryAttValueReadDAO categoryAttValueReadDAO;
	
        @Override
        public List<CategoryAttValuePO> findAll(CategoryAttValuePO po) {
            return categoryAttValueReadDAO.findAll(po,null);
        }
	
}
	