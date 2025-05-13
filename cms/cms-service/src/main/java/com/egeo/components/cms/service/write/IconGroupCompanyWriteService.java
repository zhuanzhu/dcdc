package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.IconGroupCompanyDTO;


public interface IconGroupCompanyWriteService {

	public Long insertIconGroupCompanyWithTx(IconGroupCompanyDTO dto);

	public int updateIconGroupCompanyWithTx(IconGroupCompanyDTO dto);

	public int deleteIconGroupCompanyWithTx(IconGroupCompanyDTO dto);
}
	