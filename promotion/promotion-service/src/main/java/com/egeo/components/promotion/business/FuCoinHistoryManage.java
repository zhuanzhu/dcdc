package com.egeo.components.promotion.business;

import java.util.List;
	
import com.egeo.components.promotion.dto.FuCoinHistoryDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface FuCoinHistoryManage {

	public FuCoinHistoryDTO findFuCoinHistoryById(FuCoinHistoryDTO dto);	

	public PageResult<FuCoinHistoryDTO> findFuCoinHistoryOfPage(FuCoinHistoryDTO dto,Pagination page);

	public List<FuCoinHistoryDTO> findFuCoinHistoryAll(FuCoinHistoryDTO dto);

	Long insertFuCoinHistoryWithTx(FuCoinHistoryDTO dto);

	int updateFuCoinHistoryWithTx(FuCoinHistoryDTO dto);

	int deleteFuCoinHistoryWithTx(FuCoinHistoryDTO dto);
}
	