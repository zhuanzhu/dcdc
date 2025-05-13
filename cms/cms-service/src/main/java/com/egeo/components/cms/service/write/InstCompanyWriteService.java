package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.InstCompanyDTO;


public interface InstCompanyWriteService {

	public Long insertInstCompanyWithTx(InstCompanyDTO dto);

	public int updateInstCompanyWithTx(InstCompanyDTO dto);

	public int deleteInstCompanyWithTx(InstCompanyDTO dto);
}
	