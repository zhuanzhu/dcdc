package com.egeo.components.product.service.read;

import java.util.List;

import com.egeo.components.product.dto.ProductCauseDTO;

public interface ProductCauseReadService {

    List<ProductCauseDTO> findByProductId(Long productId);
}
	