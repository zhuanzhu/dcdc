package com.egeo.components.product.service.read;

import java.util.List;

import com.egeo.components.product.dto.CategoryAttValueDTO;

public interface CategoryAttValueReadService {

    List<CategoryAttValueDTO> findAll(CategoryAttValueDTO dto);
}
	