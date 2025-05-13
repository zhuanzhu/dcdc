package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.SuSerachKeywordDTO;


public interface SuSerachKeywordWriteService {

	public Long insertSuSerachKeywordWithTx(SuSerachKeywordDTO dto);

	public int updateSuSerachKeywordWithTx(SuSerachKeywordDTO dto);

	public int deleteSuSerachKeywordWithTx(SuSerachKeywordDTO dto);
}
	