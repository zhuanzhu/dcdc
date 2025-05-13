package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.ProductCauseDTO;

public interface ProductCauseWriteService {

    Long saveProductCauseWithTx(ProductCauseDTO dto);
}
	