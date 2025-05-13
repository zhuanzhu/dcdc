package com.egeo.components.product.service.read;

import java.util.List;

import com.egeo.components.product.dto.ProductDescriptionDTO;

public interface ProductDescriptionReadService {

    List<ProductDescriptionDTO> findAll(ProductDescriptionDTO dto);

    ProductDescriptionDTO findById(ProductDescriptionDTO dto);
}
	