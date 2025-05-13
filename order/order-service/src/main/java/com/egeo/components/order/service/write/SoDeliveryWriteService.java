package com.egeo.components.order.service.write;

import com.egeo.components.order.dto.SoDeliveryDTO;


public interface SoDeliveryWriteService {

	public int insertSoDeliveryWithTx(SoDeliveryDTO dto);

	public int updateSoDeliveryWithTx(SoDeliveryDTO dto);

	public int deleteSoDeliveryWithTx(SoDeliveryDTO dto);
}
	