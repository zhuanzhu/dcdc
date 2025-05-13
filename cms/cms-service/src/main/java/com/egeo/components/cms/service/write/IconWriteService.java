package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.IconDTO;


public interface IconWriteService {

	public Long insertIconWithTx(IconDTO dto);

	public int updateIconWithTx(IconDTO dto);

	public int deleteIconWithTx(IconDTO dto);
}
	