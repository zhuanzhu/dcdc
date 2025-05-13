package com.egeo.components.user.service.write;

import java.util.List;

import com.egeo.components.user.dto.UrlWhiteListDTO;


public interface UrlWhiteListWriteService {

	public Long insertUrlWhiteListWithTx(UrlWhiteListDTO dto, List<Long> platformIdList);

	public int updateUrlWhiteListWithTx(UrlWhiteListDTO dto, List<Long> platformIdList);

	public int deleteUrlWhiteListWithTx(UrlWhiteListDTO dto);
}
	