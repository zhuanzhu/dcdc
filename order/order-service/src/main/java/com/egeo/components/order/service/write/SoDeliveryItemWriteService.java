package com.egeo.components.order.service.write;

import com.egeo.components.order.dto.SoDeliveryItemDTO;


public interface SoDeliveryItemWriteService {

	public int insertSoDeliveryItemWithTx(SoDeliveryItemDTO dto);

	public int updateSoDeliveryItemWithTx(SoDeliveryItemDTO dto);

	public int deleteSoDeliveryItemWithTx(SoDeliveryItemDTO dto);
}
	