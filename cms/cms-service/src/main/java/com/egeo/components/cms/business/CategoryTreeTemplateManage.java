package com.egeo.components.cms.business;

import java.util.List;
import java.util.Map;
/*
import com.egeo.components.cms.dto.CategoryTreeTemplateDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	*/

import com.egeo.components.cms.dto.CategoryTreeTemplateDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CategoryTreeTemplateManage {

	public CategoryTreeTemplateDTO findCategoryTreeTemplateById(CategoryTreeTemplateDTO dto);	

	public PageResult<CategoryTreeTemplateDTO> findCategoryTreeTemplateOfPage(CategoryTreeTemplateDTO dto,Pagination page);

	public List<Map<String, Object>> findCategoryTreeTemplateAll(CategoryTreeTemplateDTO dto);

	Long insertCategoryTreeTemplateWithTx(CategoryTreeTemplateDTO dto);

	int updateCategoryTreeTemplateWithTx(CategoryTreeTemplateDTO dto);

	int deleteCategoryTreeTemplateWithTx(CategoryTreeTemplateDTO dto);
}
	