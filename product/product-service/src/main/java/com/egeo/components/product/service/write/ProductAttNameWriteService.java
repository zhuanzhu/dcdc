package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.ProductAttNameDTO;

import java.util.List;

public interface ProductAttNameWriteService {

    Long saveProductAttNameWithTx(ProductAttNameDTO dto);

    String deleteByProductIdWithTx(Long productId);

    void saveProductAttName(List<Long> productAttNameIdList, List<Long> productIdList);
}
	