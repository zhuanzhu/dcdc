package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.SuListDTO;


public interface SuListWriteService {

	public Long insertSuListWithTx(SuListDTO dto);

	public int updateSuListWithTx(SuListDTO dto);

	public int deleteSuListWithTx(SuListDTO dto);
}
	