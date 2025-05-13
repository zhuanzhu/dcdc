package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.IconCompanyDTO;


public interface IconCompanyWriteService {

	public Long insertIconCompanyWithTx(IconCompanyDTO dto);

	public int updateIconCompanyWithTx(IconCompanyDTO dto);

	public int deleteIconCompanyWithTx(IconCompanyDTO dto);
}
	