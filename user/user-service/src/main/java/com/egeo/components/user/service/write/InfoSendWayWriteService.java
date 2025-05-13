package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.InfoSendWayDTO;


public interface InfoSendWayWriteService {

	public Long insertInfoSendWayWithTx(InfoSendWayDTO dto);

	public int updateInfoSendWayWithTx(InfoSendWayDTO dto);

	public int deleteInfoSendWayWithTx(InfoSendWayDTO dto);
}
	