package com.egeo.components.config.service.write;

import com.egeo.components.config.dto.LogOperCodeDTO;


public interface LogOperCodeWriteService {

	public Long insertLogOperCodeWithTx(LogOperCodeDTO dto);

	public int updateLogOperCodeWithTx(LogOperCodeDTO dto);

	public int deleteLogOperCodeWithTx(LogOperCodeDTO dto);
}
	