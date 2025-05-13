package com.egeo.components.promotion.service.write;

import com.egeo.components.promotion.dto.FuCoinDTO;


public interface FuCoinWriteService {

	public Long insertFuCoinWithTx(FuCoinDTO dto);

	public int updateFuCoinWithTx(FuCoinDTO dto);

	public int deleteFuCoinWithTx(FuCoinDTO dto);
}
	