package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.SupplierProductDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SupplierProductManage {

	public SupplierProductDTO findSupplierProductById(SupplierProductDTO dto);	

	public PageResult<SupplierProductDTO> findSupplierProductOfPage(SupplierProductDTO dto,Pagination page);

	public List<SupplierProductDTO> findSupplierProductAll(SupplierProductDTO dto);

	Long insertSupplierProductWithTx(SupplierProductDTO dto);

	int updateSupplierProductWithTx(SupplierProductDTO dto);

	int deleteSupplierProductWithTx(SupplierProductDTO dto);
}
	