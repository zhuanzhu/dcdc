package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.UrlTypeDTO;


public interface UrlTypeWriteService {

	public Long insertUrlTypeWithTx(UrlTypeDTO dto);

	public int updateUrlTypeWithTx(UrlTypeDTO dto);

	public int deleteUrlTypeWithTx(UrlTypeDTO dto);
}
	