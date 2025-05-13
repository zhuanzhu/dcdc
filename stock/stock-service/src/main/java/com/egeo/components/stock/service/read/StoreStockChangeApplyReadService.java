package com.egeo.components.stock.service.read;


import java.util.List;
	
import com.egeo.components.stock.dto.StoreStockChangeApplyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StoreStockChangeApplyReadService {

	public StoreStockChangeApplyDTO findStoreStockChangeApplyById(StoreStockChangeApplyDTO dto);

	public PageResult<StoreStockChangeApplyDTO> findStoreStockChangeApplyOfPage(StoreStockChangeApplyDTO dto,Pagination page);

	public List<StoreStockChangeApplyDTO> findStoreStockChangeApplyAll(StoreStockChangeApplyDTO dto);
}
	