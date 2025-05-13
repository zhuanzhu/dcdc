package com.egeo.components.order.service.write;

import com.egeo.components.order.dto.SoFlowDTO;


public interface SoFlowWriteService {

	public Long insertSoFlowWithTx(SoFlowDTO dto);

	public int updateSoFlowWithTx(SoFlowDTO dto);

	public int deleteSoFlowWithTx(SoFlowDTO dto);
}
	