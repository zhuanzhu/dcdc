package com.egeo.components.order.service.write;

import com.egeo.components.order.dto.DeliveryCompanyDTO;


public interface DeliveryCompanyWriteService {

	public int insertDeliveryCompanyWithTx(DeliveryCompanyDTO dto);

	public int updateDeliveryCompanyWithTx(DeliveryCompanyDTO dto);

	public int deleteDeliveryCompanyWithTx(DeliveryCompanyDTO dto);
}
	