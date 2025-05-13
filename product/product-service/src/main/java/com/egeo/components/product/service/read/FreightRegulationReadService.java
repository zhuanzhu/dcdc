package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.FreightRegulationDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface FreightRegulationReadService {

	public FreightRegulationDTO findFreightRegulationById(FreightRegulationDTO dto);

	public PageResult<FreightRegulationDTO> findFreightRegulationOfPage(FreightRegulationDTO dto,Pagination page);

	public List<FreightRegulationDTO> findFreightRegulationAll(FreightRegulationDTO dto);
}
	