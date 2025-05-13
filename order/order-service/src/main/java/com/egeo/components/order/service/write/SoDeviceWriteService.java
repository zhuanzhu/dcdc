package com.egeo.components.order.service.write;

import com.egeo.components.order.dto.SoDeviceDTO;


public interface SoDeviceWriteService {

	public Long insertSoDeviceWithTx(SoDeviceDTO dto);

	public int updateSoDeviceWithTx(SoDeviceDTO dto);

	public int deleteSoDeviceWithTx(SoDeviceDTO dto);
}
	