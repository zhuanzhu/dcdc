package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.CategoryAttNameDTO;

import java.util.List;

public interface CategoryAttNameWriteService {

	String addCategoryAttNameWithTx(CategoryAttNameDTO dto);

	String deleteCategoryAttNameWithTx(CategoryAttNameDTO categoryAttNameDTO);

	Long updaCategoryAttNameWithTx(CategoryAttNameDTO categoryAttNameDTO);

	int deleteByConditionWithTx(CategoryAttNameDTO categoryAttNameDTO);

    void addCategoryAttNameListWithTx(List<CategoryAttNameDTO> list);
}
	