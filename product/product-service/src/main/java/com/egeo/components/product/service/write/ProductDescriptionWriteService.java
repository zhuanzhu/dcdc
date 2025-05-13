package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.ProductDescriptionDTO;

import java.util.List;

public interface ProductDescriptionWriteService {

    Long saveProductDescriptionWithTx(ProductDescriptionDTO dto);

    String updateProductDescriptionWithTx(ProductDescriptionDTO dto);

    String deleteByProductIdWithTx(Long productId);

    void saveProductDescription(List<Long> productIdList);

    void updateProductDescriptionSWithTx(ProductDescriptionDTO productDescriptionDTO);
}
	