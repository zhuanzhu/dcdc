package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.SurSerachKeywordDTO;


public interface SurSerachKeywordWriteService {

	public Long insertSurSerachKeywordWithTx(SurSerachKeywordDTO dto);

	public int updateSurSerachKeywordWithTx(SurSerachKeywordDTO dto);

	public int deleteSurSerachKeywordWithTx(SurSerachKeywordDTO dto);
}
	