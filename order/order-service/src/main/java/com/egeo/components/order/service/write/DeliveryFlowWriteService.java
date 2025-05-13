package com.egeo.components.order.service.write;

import com.egeo.components.order.dto.DeliveryFlowDTO;


public interface DeliveryFlowWriteService {

	public Long insertDeliveryFlowWithTx(DeliveryFlowDTO dto);

	public int updateDeliveryFlowWithTx(DeliveryFlowDTO dto);

	public int deleteDeliveryFlowWithTx(DeliveryFlowDTO dto);
}
	