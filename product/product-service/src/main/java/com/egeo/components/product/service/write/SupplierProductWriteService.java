package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.SupplierProductDTO;


public interface SupplierProductWriteService {

	public Long insertSupplierProductWithTx(SupplierProductDTO dto);

	public int updateSupplierProductWithTx(SupplierProductDTO dto);

	public int deleteSupplierProductWithTx(SupplierProductDTO dto);
}
	