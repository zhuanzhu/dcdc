package com.egeo.components.order.service.write;

import com.egeo.components.order.dto.SoRefundItemDTO;


public interface SoRefundItemWriteService {

	Long insertSoRefundItemWithTx(SoRefundItemDTO dto);

	int updateSoRefundItemWithTx(SoRefundItemDTO dto);

	int deleteSoRefundItemWithTx(SoRefundItemDTO dto);
}
	