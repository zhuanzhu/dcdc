package com.egeo.components.stock.business;

import java.util.List;
	
import com.egeo.components.stock.dto.StoreStockChangeApplyPictureDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StoreStockChangeApplyPictureManage {

	public StoreStockChangeApplyPictureDTO findStoreStockChangeApplyPictureById(StoreStockChangeApplyPictureDTO dto);	

	public PageResult<StoreStockChangeApplyPictureDTO> findStoreStockChangeApplyPictureOfPage(StoreStockChangeApplyPictureDTO dto,Pagination page);

	public List<String> findStoreStockChangeApplyPictureAll(StoreStockChangeApplyPictureDTO dto);

	Long insertStoreStockChangeApplyPictureWithTx(StoreStockChangeApplyPictureDTO dto);

	int updateStoreStockChangeApplyPictureWithTx(StoreStockChangeApplyPictureDTO dto);

	int deleteStoreStockChangeApplyPictureWithTx(StoreStockChangeApplyPictureDTO dto);
}
	