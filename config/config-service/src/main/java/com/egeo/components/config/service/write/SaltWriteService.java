package com.egeo.components.config.service.write;

import com.egeo.components.config.dto.SaltDTO;


public interface SaltWriteService {

	public Long insertSaltWithTx(SaltDTO dto);

	public int updateSaltWithTx(SaltDTO dto);

	public int deleteSaltWithTx(SaltDTO dto);
}
	