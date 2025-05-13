package com.egeo.components.stock.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.stock.dto.StoreStockChangeApplyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StoreStockChangeApplyManage {

	public StoreStockChangeApplyDTO findStoreStockChangeApplyById(StoreStockChangeApplyDTO dto);	

	public PageResult<StoreStockChangeApplyDTO> findStoreStockChangeApplyOfPage(StoreStockChangeApplyDTO dto,Pagination page);

	public List<StoreStockChangeApplyDTO> findStoreStockChangeApplyAll(StoreStockChangeApplyDTO dto);

	Long insertStoreStockChangeApplyWithTx(StoreStockChangeApplyDTO dto,List<String> pictures);

	int updateStoreStockChangeApplyWithTx(StoreStockChangeApplyDTO dto);

	int deleteStoreStockChangeApplyWithTx(StoreStockChangeApplyDTO dto);
	/**
	 * 分页查询门店库存变更信息APP端
	 * @param dto
	 * @param page
	 * @return
	 */
	public Map<String, Object> findStoreStockChangeApplyOfPageAPP(StoreStockChangeApplyDTO dto, Pagination page);
}
	