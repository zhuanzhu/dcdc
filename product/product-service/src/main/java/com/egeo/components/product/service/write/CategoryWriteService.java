package com.egeo.components.product.service.write;

import java.util.List;

import com.egeo.components.product.dto.CategoryDTO;

public interface CategoryWriteService {

	String modifyCategoryWithTX(CategoryDTO dto,List<Long> tagIdList);

	String deleteCategoryByIdWithTx(Long categoryId);
	
	Long insertCategoryWithTx(CategoryDTO dto);
}
	