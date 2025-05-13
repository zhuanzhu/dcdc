package com.egeo.components.order.service.write;

import com.egeo.components.order.dto.SoChildFlowDTO;


public interface SoChildFlowWriteService {

	public Long insertSoChildFlowWithTx(SoChildFlowDTO dto);

	public int updateSoChildFlowWithTx(SoChildFlowDTO dto);

	public int deleteSoChildFlowWithTx(SoChildFlowDTO dto);
}
	