package com.egeo.components.product.service.read;

import java.util.List;

import com.egeo.components.product.dto.AttributeNameDTO;
import com.egeo.components.product.dto.CategoryAttNameDTO;
import com.egeo.components.product.dto.CategoryAttNameValuse;

public interface CategoryAttNameReadService {

    List<CategoryAttNameDTO> categoryAttNameByCategoryId(CategoryAttNameDTO dto);

    CategoryAttNameDTO categoryAttNameByAttNameId(CategoryAttNameDTO dto);

    CategoryAttNameDTO findById(CategoryAttNameDTO dto);
    
    List<CategoryAttNameValuse> categoryAttNameByCId(CategoryAttNameDTO dto);

    List<CategoryAttNameDTO> findAll(CategoryAttNameDTO dto);

    List<AttributeNameDTO> merchantProductAttNameByCId(CategoryAttNameDTO dto);

	int isRequiredByCategoryIdAttNameId(Long categoryId, Long id);

}
	