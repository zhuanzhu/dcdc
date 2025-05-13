package com.egeo.components.order.service.write;

import com.egeo.components.order.dto.SoCustomerServiceDTO;


public interface SoCustomerServiceWriteService {

	public Long insertSoCustomerServiceWithTx(SoCustomerServiceDTO dto);

	public int updateSoCustomerServiceWithTx(SoCustomerServiceDTO dto);

	public int deleteSoCustomerServiceWithTx(SoCustomerServiceDTO dto);
}
	