package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.VersionsChildDTO;


public interface VersionsChildWriteService {

	public Long insertVersionsChildWithTx(VersionsChildDTO dto);

	public int updateVersionsChildWithTx(VersionsChildDTO dto);

	public int deleteVersionsChildWithTx(VersionsChildDTO dto);
}
	