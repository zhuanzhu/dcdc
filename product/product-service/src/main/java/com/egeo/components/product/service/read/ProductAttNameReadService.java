package com.egeo.components.product.service.read;

import java.util.List;

import com.egeo.components.product.dto.ProductAttNameDTO;
import com.egeo.components.product.dto.StandardProductUnitDTO;

public interface ProductAttNameReadService {

    List<ProductAttNameDTO> findAll(ProductAttNameDTO dto);

    ProductAttNameDTO queryIsElectronicBySpuId(StandardProductUnitDTO dto);

    ProductAttNameDTO queryIsUnitBySpuId(StandardProductUnitDTO dto);

    Long findLastId();
}
	