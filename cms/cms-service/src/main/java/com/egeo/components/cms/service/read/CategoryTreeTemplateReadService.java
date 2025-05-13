package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.CategoryTreeTemplateDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CategoryTreeTemplateReadService {

	public CategoryTreeTemplateDTO findCategoryTreeTemplateById(CategoryTreeTemplateDTO dto);

	public PageResult<CategoryTreeTemplateDTO> findCategoryTreeTemplateOfPage(CategoryTreeTemplateDTO dto,Pagination page);

	public List<CategoryTreeTemplateDTO> findCategoryTreeTemplateAll(CategoryTreeTemplateDTO dto);
}
	