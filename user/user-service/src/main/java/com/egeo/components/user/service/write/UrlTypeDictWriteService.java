package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.UrlTypeDictDTO;


public interface UrlTypeDictWriteService {

	public Long insertUrlTypeDictWithTx(UrlTypeDictDTO dto);

	public int updateUrlTypeDictWithTx(UrlTypeDictDTO dto);

	public int deleteUrlTypeDictWithTx(UrlTypeDictDTO dto);
}
	