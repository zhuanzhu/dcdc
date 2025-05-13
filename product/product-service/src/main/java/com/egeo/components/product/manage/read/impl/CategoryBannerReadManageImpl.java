package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.CategoryBannerReadManage;
import com.egeo.components.product.dao.read.CategoryBannerReadDAO;
import com.egeo.components.product.po.CategoryBannerPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CategoryBannerReadManageImpl implements CategoryBannerReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CategoryBannerReadDAO categoryBannerReadDAO;
	
	public CategoryBannerPO findCategoryBannerById(CategoryBannerPO po) {
		CategoryBannerPO categoryBannerpo = new CategoryBannerPO();
		categoryBannerpo.setId(po.getId());
		return categoryBannerReadDAO.findById(categoryBannerpo);
	}

	public PageResult<CategoryBannerPO> findCategoryBannerOfPage(CategoryBannerPO po, Pagination page) {
		
		PageResult<CategoryBannerPO> pageResult = new PageResult<CategoryBannerPO>();
		List<CategoryBannerPO> list = null;

		int cnt = categoryBannerReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = categoryBannerReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CategoryBannerPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CategoryBannerPO> findCategoryBannerAll(CategoryBannerPO po) {

		return categoryBannerReadDAO.findAll(po,null);
	}
	
}
	