package com.egeo.components.product.manage.write.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.CategoryAttNameWriteManage;
import com.egeo.components.product.dao.read.CategoryAttNameReadDAO;
import com.egeo.components.product.dao.read.CategoryTreeNodeReadDAO;
import com.egeo.components.product.dao.write.CategoryAttNameWriteDAO;
import com.egeo.components.product.po.CategoryAttNamePO;
import com.egeo.components.product.po.CategoryTreeNodePO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;

@Service
public class CategoryAttNameWriteManageImpl implements CategoryAttNameWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CategoryAttNameWriteDAO categoryAttNameWriteDAO;
	
	@Autowired
	private CategoryAttNameReadDAO categoryAttNameReadDAO;
	
	@Autowired
	private CategoryTreeNodeReadDAO categoryTreeNodeReadDAO;
	
	@Override
	public String addCategoryAttNameWithTx(CategoryAttNamePO categoryAttNamePO) {
		CategoryAttNamePO po = new CategoryAttNamePO();
		po.setAttNameId(categoryAttNamePO.getAttNameId());
		po.setCategoryId(categoryAttNamePO.getCategoryId());
		po.setPlatformId(categoryAttNamePO.getPlatformId());
		po.setParentId(categoryAttNamePO.getParentId());
		List<CategoryAttNamePO> list = categoryAttNameReadDAO.findAll(po,null);
		if(EmptyUtil.isNotEmpty(list)){
			throw new BusinessException("同一节点下，不能有2个相同名字的属性");
		}
		if(checkChildNodeIsExist(po)){
			throw new BusinessException("存在子节点，不能添加属性");
		}		
		return categoryAttNameWriteDAO.insert(categoryAttNamePO)+"";
	}
	private boolean checkChildNodeIsExist(CategoryAttNamePO po) {
		boolean rt = false;
		CategoryTreeNodePO ctnPo = new CategoryTreeNodePO();
		ctnPo.setCategoryId(po.getCategoryId());
		List<CategoryTreeNodePO> list = categoryTreeNodeReadDAO.findAll(ctnPo,null);
		if(EmptyUtil.isEmpty(list)){
			return false;
		}
		List<CategoryTreeNodePO> childNodelist = new ArrayList<CategoryTreeNodePO>();
		for(CategoryTreeNodePO ctn:list){
			CategoryTreeNodePO childtn = new CategoryTreeNodePO();
			childtn.setParentId(ctn.getId());
			List<CategoryTreeNodePO> cLIst = categoryTreeNodeReadDAO.findAll(childtn,null);
			if(EmptyUtil.isNotEmpty(cLIst)){
				childNodelist.addAll(cLIst);
			}
		}
		if(EmptyUtil.isNotEmpty(childNodelist)){
			rt = true;
		}
				
		return rt;
	}
	@Override
	public String deleteCategoryAttNameWithTx(CategoryAttNamePO po) {
		return categoryAttNameWriteDAO.delete(po)+"";
	}
	@Override
	public Long updaCategoryAttNameWithTx(CategoryAttNamePO po) {
		categoryAttNameWriteDAO.update(po);
		return po.getId();
	}
	@Override
	public int deleteByConditionWithTx(CategoryAttNamePO po) {
		return categoryAttNameWriteDAO.deleteByPara(po);
	}

	@Override
	public void addCategoryAttNameListWithTx(List<CategoryAttNamePO> categoryAttNamePOS) {
		categoryAttNameWriteDAO.addCategoryAttNameListWithTx(categoryAttNamePOS);
	}
}
	