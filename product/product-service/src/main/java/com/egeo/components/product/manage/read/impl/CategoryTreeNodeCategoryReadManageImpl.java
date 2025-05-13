package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.CategoryTreeNodeCategoryReadManage;
import com.egeo.components.product.dao.read.CategoryTreeNodeCategoryReadDAO;
import com.egeo.components.product.po.CategoryTreeNodeCategoryPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CategoryTreeNodeCategoryReadManageImpl implements CategoryTreeNodeCategoryReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CategoryTreeNodeCategoryReadDAO categoryTreeNodeCategoryReadDAO;
	
	public CategoryTreeNodeCategoryPO findCategoryTreeNodeCategoryById(CategoryTreeNodeCategoryPO po) {
		CategoryTreeNodeCategoryPO categoryTreeNodeCategorypo = new CategoryTreeNodeCategoryPO();
		categoryTreeNodeCategorypo.setId(po.getId());
		return categoryTreeNodeCategoryReadDAO.findById(categoryTreeNodeCategorypo);
	}

	public PageResult<CategoryTreeNodeCategoryPO> findCategoryTreeNodeCategoryOfPage(CategoryTreeNodeCategoryPO po, Pagination page) {
		
		PageResult<CategoryTreeNodeCategoryPO> pageResult = new PageResult<CategoryTreeNodeCategoryPO>();
		List<CategoryTreeNodeCategoryPO> list = null;

		int cnt = categoryTreeNodeCategoryReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = categoryTreeNodeCategoryReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CategoryTreeNodeCategoryPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CategoryTreeNodeCategoryPO> findCategoryTreeNodeCategoryAll(CategoryTreeNodeCategoryPO po) {

		return categoryTreeNodeCategoryReadDAO.findAll(po,null);
	}

	@Override
	public List<Long> findCategoryIdsByCategoryTreeNodeId(List<Long> categoryTreeNodeIds) {
		return categoryTreeNodeCategoryReadDAO.findCategoryIdsByCategoryTreeNodeIds(categoryTreeNodeIds);
	}

}
	