package com.egeo.components.config.service.write;

import com.egeo.components.config.dto.KeywordDTO;


public interface KeywordWriteService {

	public Long insertKeywordWithTx(KeywordDTO dto);

	public int updateKeywordWithTx(KeywordDTO dto);

	public int deleteKeywordWithTx(KeywordDTO dto);
}
	