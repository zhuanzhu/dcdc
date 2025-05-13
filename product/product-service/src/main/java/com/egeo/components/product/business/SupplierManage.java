package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.SupplierDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SupplierManage {

	public SupplierDTO findSupplierById(SupplierDTO dto);	

	public PageResult<SupplierDTO> findSupplierOfPage(SupplierDTO dto,Pagination page);

	public List<SupplierDTO> findSupplierAll(SupplierDTO dto);

	Long insertSupplierWithTx(SupplierDTO dto);

	int updateSupplierWithTx(SupplierDTO dto);

	int deleteSupplierWithTx(SupplierDTO dto);
}
	