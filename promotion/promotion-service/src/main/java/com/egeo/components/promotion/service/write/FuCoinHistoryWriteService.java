package com.egeo.components.promotion.service.write;

import com.egeo.components.promotion.dto.FuCoinHistoryDTO;


public interface FuCoinHistoryWriteService {

	public Long insertFuCoinHistoryWithTx(FuCoinHistoryDTO dto);

	public int updateFuCoinHistoryWithTx(FuCoinHistoryDTO dto);

	public int deleteFuCoinHistoryWithTx(FuCoinHistoryDTO dto);
}
	