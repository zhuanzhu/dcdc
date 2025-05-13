package com.egeo.components.product.service.read;

import java.util.List;

import com.egeo.components.product.dto.ProductAttValueDTO;

public interface ProductAttValueReadService {

    List<ProductAttValueDTO> findAll(ProductAttValueDTO dto);
}
	