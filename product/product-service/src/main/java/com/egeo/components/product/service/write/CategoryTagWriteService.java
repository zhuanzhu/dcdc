package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.CategoryTagDTO;


public interface CategoryTagWriteService {

	public Long insertCategoryTagWithTx(CategoryTagDTO dto);

	public int updateCategoryTagWithTx(CategoryTagDTO dto);

	public int deleteCategoryTagWithTx(CategoryTagDTO dto);
}
	