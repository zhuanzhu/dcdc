package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.BrandDTO;

public interface BrandWriteService {

    Long saveBrandWithTx(BrandDTO dto);

    String updateBrandWithTx(BrandDTO dto);
    
}
	