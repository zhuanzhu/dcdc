package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.StandardUnitCombinationDTO;


public interface StandardUnitCombinationWriteService {

	public Long insertStandardUnitCombinationWithTx(StandardUnitCombinationDTO dto);

	public int updateStandardUnitCombinationWithTx(StandardUnitCombinationDTO dto);

	public int deleteStandardUnitCombinationWithTx(StandardUnitCombinationDTO dto);
}
	