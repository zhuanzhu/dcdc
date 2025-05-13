package com.egeo.components.order.service.write;

import com.egeo.components.order.dto.SoUnitDTO;


public interface SoUnitWriteService {

	public Long insertSoUnitWithTx(SoUnitDTO dto);

	public int updateSoUnitWithTx(SoUnitDTO dto);

	public int deleteSoUnitWithTx(SoUnitDTO dto);
}
	