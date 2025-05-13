package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.IconGroupDTO;


public interface IconGroupWriteService {

	public Long insertIconGroupWithTx(IconGroupDTO dto);

	public int updateIconGroupWithTx(IconGroupDTO dto);

	public int deleteIconGroupWithTx(IconGroupDTO dto);
}
	