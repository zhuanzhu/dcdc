package com.egeo.components.product.manage.read.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.CategoryTreeNodeReadManage;
import com.egeo.components.product.condition.CategoryTreeNodeCondition;
import com.egeo.components.product.dao.read.CategoryTreeNodeReadDAO;
import com.egeo.components.product.po.CategoryTreeNodePO;

@Service
public class CategoryTreeNodeReadManageImpl implements CategoryTreeNodeReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CategoryTreeNodeReadDAO categoryTreeNodeReadDAO;

	@Override
	public List<CategoryTreeNodeCondition> getCategoryTreeByCatId(Long categoryTreeId, Long platformId) {
		CategoryTreeNodePO po = new CategoryTreeNodePO();
		po.setPlatformId(platformId);
		po.setCategoryTreeId(categoryTreeId);
		return categoryTreeNodeReadDAO.getCategoryTreeByCatId(po);
	}

	@Override
	public CategoryTreeNodePO CategoryTreeNodeByCategoryId(Long parentId) {
		CategoryTreeNodePO categoryTreeNodePO = new CategoryTreeNodePO();
		categoryTreeNodePO.setId(parentId);
		return categoryTreeNodeReadDAO.findById(categoryTreeNodePO);
	}

	@Override
	public CategoryTreeNodePO findById(CategoryTreeNodePO po) {
		return categoryTreeNodeReadDAO.findById(po);
	}

	@Override
	public List<CategoryTreeNodePO> findAll(CategoryTreeNodePO po) {
		return categoryTreeNodeReadDAO.findAll(po,null);
	}

	@Override
	public CategoryTreeNodeCondition findCategoryTreeNodeInofByNodeId(Long ctnId) {
		return categoryTreeNodeReadDAO.findCategoryTreeNodeInofByNodeId(ctnId);
	}

	@Override
	public Integer listSortMax() {
		return categoryTreeNodeReadDAO.listSortMax();
	}

	/**
	 * 根据条件查询类目节点信息
	 * 
	 * @param po
	 * @return
	 */
	@Override
	public List<CategoryTreeNodeCondition> findCategoryTreeNodeAll(CategoryTreeNodePO po) {
		// TODO Auto-generated method stub
		return categoryTreeNodeReadDAO.findCategoryTreeNodeAll(po,null);
	}

	/**
	 * 根据前台类目节点id查询前台类目节点信息
	 * 
	 * @param categoryTreeNodeId
	 * @return
	 */
	@Override
	public CategoryTreeNodeCondition findByCategoryTreeNodeId(Long categoryTreeNodeId) {
		// TODO Auto-generated method stub
		return categoryTreeNodeReadDAO.findByCategoryTreeNodeId(categoryTreeNodeId);
	}

	@Override
	public List<CategoryTreeNodeCondition> findWebCategoryTreeByCategoryTreeId(CategoryTreeNodePO po) {
		return categoryTreeNodeReadDAO.findWebCategoryTreeByCategoryTreeId(po);
	}

	@Override
	public String findMaxSubSerialNumber(Long categoryTreeId, Long parentId) {
		return categoryTreeNodeReadDAO.findMaxSubSerialNumber(categoryTreeId, parentId);
	}

	@Override
	public Long findParentidById(Long id) {
		return categoryTreeNodeReadDAO.findParentidById(id) ;
	}

}
