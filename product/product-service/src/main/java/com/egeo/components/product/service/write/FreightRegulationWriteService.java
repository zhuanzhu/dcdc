package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.FreightRegulationDTO;


public interface FreightRegulationWriteService {

	public Long insertFreightRegulationWithTx(FreightRegulationDTO dto);

	public int updateFreightRegulationWithTx(FreightRegulationDTO dto);

	public int deleteFreightRegulationWithTx(FreightRegulationDTO dto);
}
	