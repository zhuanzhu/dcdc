package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.CategoryTreeNodeRelationDTO;

public interface CategoryTreeNodeRelationWriteService {

	String addCategoryTreeNodeWithTx(CategoryTreeNodeRelationDTO dto);

	String deleteCategoryTreeNodeWithTx(CategoryTreeNodeRelationDTO dto);


}
	