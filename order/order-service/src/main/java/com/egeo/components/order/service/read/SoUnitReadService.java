package com.egeo.components.order.service.read;


import java.util.List;
	
import com.egeo.components.order.dto.SoUnitDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface SoUnitReadService {

	public SoUnitDTO findSoUnitById(SoUnitDTO dto);

	public PageResult<SoUnitDTO> findSoUnitOfPage(SoUnitDTO dto,Pagination page);

	public List<SoUnitDTO> findSoUnitAll(SoUnitDTO dto);
}
	