package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.FreightRegulationDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface FreightRegulationManage {

	public FreightRegulationDTO findFreightRegulationById(FreightRegulationDTO dto);	

	public PageResult<FreightRegulationDTO> findFreightRegulationOfPage(FreightRegulationDTO dto,Pagination page);

	public List<FreightRegulationDTO> findFreightRegulationAll(FreightRegulationDTO dto);

	Long insertFreightRegulationWithTx(FreightRegulationDTO dto);

	int updateFreightRegulationWithTx(FreightRegulationDTO dto);

	int deleteFreightRegulationWithTx(FreightRegulationDTO dto);
}
	