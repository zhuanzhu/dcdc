package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.VersionsDTO;


public interface VersionsWriteService {

	public Long insertVersionsWithTx(VersionsDTO dto);

	public int updateVersionsWithTx(VersionsDTO dto);

	public int deleteVersionsWithTx(VersionsDTO dto);

	public int updateVersionsOfficialByTypeWithTx(Integer user);
}
	