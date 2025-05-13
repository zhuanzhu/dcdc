package com.egeo.components.product.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.CategoryWriteManage;
import com.egeo.components.product.dao.write.CategoryTagWriteDAO;
import com.egeo.components.product.dao.write.CategoryTreeNodeWriteDAO;
import com.egeo.components.product.dao.write.CategoryWriteDAO;
import com.egeo.components.product.po.CategoryPO;
import com.egeo.components.product.po.CategoryTagPO;
import com.egeo.components.product.po.CategoryTreeNodePO;
import com.egeo.utils.EmptyUtil;

@Service
public class CategoryWriteManageImpl implements CategoryWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CategoryWriteDAO categoryWriteDAO;
	
	@Autowired
	private CategoryTreeNodeWriteDAO categoryTreeNodeWriteDAO;
	
	@Autowired
	private CategoryTagWriteDAO categoryTagWriteDAO;
	
	@Override
	public String modifyCategoryWithTX(CategoryPO po,List<Long> tagIdList) {
		if(EmptyUtil.isNotEmpty(tagIdList)){
			CategoryTagPO categoryTagPO = new CategoryTagPO();
			categoryTagPO.setCategoryId(po.getId());
			categoryTagWriteDAO.deleteByPara(categoryTagPO);
			
			for (Long tagId : tagIdList) {
				CategoryTagPO categoryTagPO2 = new CategoryTagPO();
				categoryTagPO2.setTagId(tagId);
				categoryTagPO2.setCategoryId(po.getId());
				categoryTagWriteDAO.insert(categoryTagPO2);
			}
			
		}
		
		return categoryWriteDAO.update(po)+"";
	}
	@Override
	public String deleteCategoryByIdWithTx(Long categoryId) {
		
		CategoryPO po = new CategoryPO();
		po.setId(categoryId);
		CategoryTreeNodePO ctnPo = new CategoryTreeNodePO();
		
		ctnPo.setCategoryId(categoryId);
		
		return categoryWriteDAO.delete(po)+categoryTreeNodeWriteDAO.deleteByPara(ctnPo)+"";
	}
	@Override
	public Long insertCategoryWithTx(CategoryPO po) {
		categoryWriteDAO.insert(po);
		return po.getId();
	}
}
	