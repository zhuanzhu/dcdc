package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.SupplierDTO;


public interface SupplierWriteService {

	public Long insertSupplierWithTx(SupplierDTO dto);

	public int updateSupplierWithTx(SupplierDTO dto);

	public int deleteSupplierWithTx(SupplierDTO dto);
}
	