package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.CategoryTagReadManage;
import com.egeo.components.product.dao.read.CategoryTagReadDAO;
import com.egeo.components.product.po.CategoryTagPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CategoryTagReadManageImpl implements CategoryTagReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CategoryTagReadDAO categoryTagReadDAO;
	
	public CategoryTagPO findCategoryTagById(CategoryTagPO po) {
		CategoryTagPO categoryTagpo = new CategoryTagPO();
		categoryTagpo.setId(po.getId());
		return categoryTagReadDAO.findById(categoryTagpo);
	}

	public PageResult<CategoryTagPO> findCategoryTagOfPage(CategoryTagPO po, Pagination page) {
		
		PageResult<CategoryTagPO> pageResult = new PageResult<CategoryTagPO>();
		List<CategoryTagPO> list = null;

		int cnt = categoryTagReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = categoryTagReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CategoryTagPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CategoryTagPO> findCategoryTagAll(CategoryTagPO po) {

		return categoryTagReadDAO.findAll(po,null);
	}
	
}
	