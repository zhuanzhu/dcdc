package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.SupplierDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface SupplierReadService {

	public SupplierDTO findSupplierById(SupplierDTO dto);

	public PageResult<SupplierDTO> findSupplierOfPage(SupplierDTO dto,Pagination page);

	public List<SupplierDTO> findSupplierAll(SupplierDTO dto);

	List<SupplierDTO> findByIdList(List<Long> ids);

}
	