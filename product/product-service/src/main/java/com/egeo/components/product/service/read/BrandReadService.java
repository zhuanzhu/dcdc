package com.egeo.components.product.service.read;

import java.util.List;

import com.egeo.components.product.dto.BrandDTO;

public interface BrandReadService {
    List<BrandDTO> findAll(BrandDTO dto);

	BrandDTO findById(BrandDTO dto);
}
	