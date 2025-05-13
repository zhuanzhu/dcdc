package com.egeo.components.promotion.service.write;

import com.egeo.components.promotion.dto.ECardTempDTO;


public interface ECardTempWriteService {

	public Long insertECardTempWithTx(ECardTempDTO dto);

	public int updateECardTempWithTx(ECardTempDTO dto);

	public int deleteECardTempWithTx(ECardTempDTO dto);
}
	