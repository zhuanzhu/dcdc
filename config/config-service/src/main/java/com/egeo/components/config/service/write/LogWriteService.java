package com.egeo.components.config.service.write;

import com.egeo.components.config.dto.LogDTO;


public interface LogWriteService {

	public Long insertLogWithTx(LogDTO dto);

	public int updateLogWithTx(LogDTO dto);

	public int deleteLogWithTx(LogDTO dto);
}
	