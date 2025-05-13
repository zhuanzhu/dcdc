package com.egeo.components.config.service.write;

import com.egeo.components.config.dto.PolymallUserDTO;


public interface PolymallUserWriteService {

	public Long insertPolymallUserWithTx(PolymallUserDTO dto);

	public int updatePolymallUserWithTx(PolymallUserDTO dto);

	public int deletePolymallUserWithTx(PolymallUserDTO dto);
}
	