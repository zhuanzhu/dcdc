package com.egeo.components.promotion.business;

import java.util.List;
	
import com.egeo.components.promotion.dto.FuCoinDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface FuCoinManage {

	public FuCoinDTO findFuCoinById(FuCoinDTO dto);	

	public PageResult<FuCoinDTO> findFuCoinOfPage(FuCoinDTO dto,Pagination page);

	public List<FuCoinDTO> findFuCoinAll(FuCoinDTO dto);

	Long insertFuCoinWithTx(FuCoinDTO dto);

	int updateFuCoinWithTx(FuCoinDTO dto);

	int deleteFuCoinWithTx(FuCoinDTO dto);
}
	