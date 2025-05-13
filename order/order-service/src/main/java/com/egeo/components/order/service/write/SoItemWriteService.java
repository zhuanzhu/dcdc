package com.egeo.components.order.service.write;

import com.egeo.components.order.dto.SoItemDTO;

public interface SoItemWriteService {
	/**
	 * 修改订单项
	 * @param dto
	 * @return
	 */
	Long updateSoItemWithTx(SoItemDTO dto);

	int updateSoItemRefundWithTx(SoItemDTO dto);
}
