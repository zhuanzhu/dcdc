package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.CategoryTreeTemplateDTO;


public interface CategoryTreeTemplateWriteService {

	public Long insertCategoryTreeTemplateWithTx(CategoryTreeTemplateDTO dto);

	public int updateCategoryTreeTemplateWithTx(CategoryTreeTemplateDTO dto);

	public int deleteCategoryTreeTemplateWithTx(CategoryTreeTemplateDTO dto);
}
	