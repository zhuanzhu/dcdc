package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.CategoryAttValueDTO;

public interface CategoryAttValueWriteService {

    Long saveCategoryAttValueWithTx(CategoryAttValueDTO dto);
}
	