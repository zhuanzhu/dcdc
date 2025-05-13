package com.egeo.components.stock.service.read;


import java.util.List;
	
import com.egeo.components.stock.dto.StoreStockChangeApplyPictureDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StoreStockChangeApplyPictureReadService {

	public StoreStockChangeApplyPictureDTO findStoreStockChangeApplyPictureById(StoreStockChangeApplyPictureDTO dto);

	public PageResult<StoreStockChangeApplyPictureDTO> findStoreStockChangeApplyPictureOfPage(StoreStockChangeApplyPictureDTO dto,Pagination page);

	public List<StoreStockChangeApplyPictureDTO> findStoreStockChangeApplyPictureAll(StoreStockChangeApplyPictureDTO dto);
}
	