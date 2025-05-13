package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.MpSerachKeywordDTO;


public interface MpSerachKeywordWriteService {

	public Long insertMpSerachKeywordWithTx(MpSerachKeywordDTO dto);

	public int updateMpSerachKeywordWithTx(MpSerachKeywordDTO dto);

	public int deleteMpSerachKeywordWithTx(MpSerachKeywordDTO dto);
}
	