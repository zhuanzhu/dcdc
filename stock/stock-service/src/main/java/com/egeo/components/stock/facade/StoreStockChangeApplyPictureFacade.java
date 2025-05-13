package com.egeo.components.stock.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.stock.service.read.StoreStockChangeApplyPictureReadService;
import com.egeo.components.stock.service.write.StoreStockChangeApplyPictureWriteService;
import com.egeo.components.stock.dto.StoreStockChangeApplyPictureDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class StoreStockChangeApplyPictureFacade {
	
	@Resource
	private StoreStockChangeApplyPictureReadService storeStockChangeApplyPictureReadService;
	
	@Resource
	private StoreStockChangeApplyPictureWriteService storeStockChangeApplyPictureWriteService;
	
	
	public StoreStockChangeApplyPictureDTO findStoreStockChangeApplyPictureById(StoreStockChangeApplyPictureDTO dto){
		
		return storeStockChangeApplyPictureReadService.findStoreStockChangeApplyPictureById(dto);
	}

	public PageResult<StoreStockChangeApplyPictureDTO> findStoreStockChangeApplyPictureOfPage(StoreStockChangeApplyPictureDTO dto,Pagination page){
		
		return storeStockChangeApplyPictureReadService.findStoreStockChangeApplyPictureOfPage(dto, page);
		
	}

	public List<StoreStockChangeApplyPictureDTO> findStoreStockChangeApplyPictureAll(StoreStockChangeApplyPictureDTO dto){
		
		return storeStockChangeApplyPictureReadService.findStoreStockChangeApplyPictureAll(dto);
		
	}

	public Long insertStoreStockChangeApplyPictureWithTx(StoreStockChangeApplyPictureDTO dto){
		
		return storeStockChangeApplyPictureWriteService.insertStoreStockChangeApplyPictureWithTx(dto);
	}

	public int updateStoreStockChangeApplyPictureWithTx(StoreStockChangeApplyPictureDTO dto){
		
		return storeStockChangeApplyPictureWriteService.updateStoreStockChangeApplyPictureWithTx(dto);
	}

	public int deleteStoreStockChangeApplyPictureWithTx(StoreStockChangeApplyPictureDTO dto){
		
		return storeStockChangeApplyPictureWriteService.deleteStoreStockChangeApplyPictureWithTx(dto);
		
	}

}
	