package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.LocalParamDTO;


public interface LocalParamWriteService {

	public Long insertLocalParamWithTx(LocalParamDTO dto);

	public int updateLocalParamWithTx(LocalParamDTO dto);

	public int deleteLocalParamWithTx(LocalParamDTO dto);
}
	