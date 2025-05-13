package com.egeo.components.config.service.write;

import com.egeo.components.config.dto.LogDictDTO;


public interface LogDictWriteService {

	public Long insertLogDictWithTx(LogDictDTO dto);

	public int updateLogDictWithTx(LogDictDTO dto);

	public int deleteLogDictWithTx(LogDictDTO dto);
}
	