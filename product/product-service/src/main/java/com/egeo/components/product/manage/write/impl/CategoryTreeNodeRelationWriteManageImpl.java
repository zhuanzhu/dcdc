package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.CategoryTreeNodeRelationWriteManage;
import com.egeo.components.product.dao.write.CategoryTreeNodeRelationWriteDAO;
import com.egeo.components.product.po.CategoryTreeNodeRelationPO;

@Service
public class CategoryTreeNodeRelationWriteManageImpl implements CategoryTreeNodeRelationWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CategoryTreeNodeRelationWriteDAO categoryTreeNodeRelationWriteDAO;
	@Override
	public String addCategoryTreeNodeWithTx(CategoryTreeNodeRelationPO po) {
		
		return categoryTreeNodeRelationWriteDAO.insert(po)+"";
	}
	@Override
	public String deleteCategoryTreeNodeWithTx(CategoryTreeNodeRelationPO po) {
		
		return categoryTreeNodeRelationWriteDAO.delete(po)+"";
	}
}
	