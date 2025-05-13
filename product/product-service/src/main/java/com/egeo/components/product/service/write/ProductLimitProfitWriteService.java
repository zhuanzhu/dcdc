package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.ProductLimitProfitDTO;


public interface ProductLimitProfitWriteService {

	public Long insertProductLimitProfitWithTx(ProductLimitProfitDTO dto);

	public int updateProductLimitProfitWithTx(ProductLimitProfitDTO dto);

	public int deleteProductLimitProfitWithTx(ProductLimitProfitDTO dto);
}
	