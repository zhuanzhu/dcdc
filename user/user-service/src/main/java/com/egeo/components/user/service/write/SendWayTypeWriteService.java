package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.SendWayTypeDTO;


public interface SendWayTypeWriteService {

	public Long insertSendWayTypeWithTx(SendWayTypeDTO dto);

	public int updateSendWayTypeWithTx(SendWayTypeDTO dto);

	public int deleteSendWayTypeWithTx(SendWayTypeDTO dto);
}
	