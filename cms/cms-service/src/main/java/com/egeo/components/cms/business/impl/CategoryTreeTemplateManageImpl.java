package com.egeo.components.cms.business.impl;
	

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.cms.business.CategoryTreeTemplateManage;
import com.egeo.components.cms.facade.CategoryTreeTemplateFacade;
import com.egeo.components.cms.dto.CategoryTreeTemplateDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("categoryTreeTemplate")
public class CategoryTreeTemplateManageImpl implements CategoryTreeTemplateManage{

	
	@Resource(name="categoryTreeTemplateFacade")
	private CategoryTreeTemplateFacade categoryTreeTemplateFacade;

	@Override
	public CategoryTreeTemplateDTO findCategoryTreeTemplateById(CategoryTreeTemplateDTO dto) {
		return categoryTreeTemplateFacade.findCategoryTreeTemplateById(dto);
	}

	@Override
	public PageResult<CategoryTreeTemplateDTO> findCategoryTreeTemplateOfPage(CategoryTreeTemplateDTO dto, Pagination page) {
		return categoryTreeTemplateFacade.findCategoryTreeTemplateOfPage(dto, page);
	}

	@Override
	public List<Map<String, Object>> findCategoryTreeTemplateAll(CategoryTreeTemplateDTO dto) {
		return categoryTreeTemplateFacade.findCategoryTreeTemplateAll(dto);
	}

	@Override
	public Long insertCategoryTreeTemplateWithTx(CategoryTreeTemplateDTO dto) {
		return categoryTreeTemplateFacade.insertCategoryTreeTemplateWithTx(dto);
	}

	@Override
	public int updateCategoryTreeTemplateWithTx(CategoryTreeTemplateDTO dto) {
		return categoryTreeTemplateFacade.updateCategoryTreeTemplateWithTx(dto);
	}

	@Override
	public int deleteCategoryTreeTemplateWithTx(CategoryTreeTemplateDTO dto) {
		return categoryTreeTemplateFacade.deleteCategoryTreeTemplateWithTx(dto);
	}


}
	