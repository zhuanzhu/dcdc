package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.UrlDTO;

public interface UrlWriteService {

	int saveOrUpdateWithTx(UrlDTO dto);

	void deleteWithTx(UrlDTO dto);

}
	