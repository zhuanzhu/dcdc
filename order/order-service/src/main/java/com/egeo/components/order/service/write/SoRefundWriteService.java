package com.egeo.components.order.service.write;

import com.egeo.components.order.dto.SoRefundDTO;


public interface SoRefundWriteService {

	public Long insertSoRefundWithTx(SoRefundDTO dto);

	public int updateSoRefundWithTx(SoRefundDTO dto);

	public int deleteSoRefundWithTx(SoRefundDTO dto);
}
	