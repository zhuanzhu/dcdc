package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.StandardUnitClientDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StandardUnitClientReadService {

	public StandardUnitClientDTO findStandardUnitClientById(StandardUnitClientDTO dto);

	public PageResult<StandardUnitClientDTO> findStandardUnitClientOfPage(StandardUnitClientDTO dto,Pagination page);

	public List<StandardUnitClientDTO> findStandardUnitClientAll(StandardUnitClientDTO dto);
}
	