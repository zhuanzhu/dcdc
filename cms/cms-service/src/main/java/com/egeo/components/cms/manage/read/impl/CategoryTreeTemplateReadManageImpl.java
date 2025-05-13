package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.CategoryTreeTemplateReadManage;
import com.egeo.components.cms.dao.read.CategoryTreeTemplateReadDAO;
import com.egeo.components.cms.po.CategoryTreeTemplatePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CategoryTreeTemplateReadManageImpl implements CategoryTreeTemplateReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CategoryTreeTemplateReadDAO categoryTreeTemplateReadDAO;
	
	public CategoryTreeTemplatePO findCategoryTreeTemplateById(CategoryTreeTemplatePO po) {
		CategoryTreeTemplatePO categoryTreeTemplatepo = new CategoryTreeTemplatePO();
		categoryTreeTemplatepo.setId(po.getId());
		return categoryTreeTemplateReadDAO.findById(categoryTreeTemplatepo);
	}

	public PageResult<CategoryTreeTemplatePO> findCategoryTreeTemplateOfPage(CategoryTreeTemplatePO po, Pagination page) {
		
		PageResult<CategoryTreeTemplatePO> pageResult = new PageResult<CategoryTreeTemplatePO>();
		List<CategoryTreeTemplatePO> list = null;

		int cnt = categoryTreeTemplateReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = categoryTreeTemplateReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CategoryTreeTemplatePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CategoryTreeTemplatePO> findCategoryTreeTemplateAll(CategoryTreeTemplatePO po) {

		return categoryTreeTemplateReadDAO.findAll(po,null);
	}
	
}
	