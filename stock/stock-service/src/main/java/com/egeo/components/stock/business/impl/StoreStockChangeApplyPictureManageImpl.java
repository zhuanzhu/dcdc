package com.egeo.components.stock.business.impl;
	

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.business.StoreStockChangeApplyPictureManage;
import com.egeo.components.stock.facade.StoreStockChangeApplyPictureFacade;
import com.egeo.components.stock.dto.StoreStockChangeApplyPictureDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("storeStockChangeApplyPicture")
public class StoreStockChangeApplyPictureManageImpl implements StoreStockChangeApplyPictureManage{

	
	@Resource(name="storeStockChangeApplyPictureFacade")
	private StoreStockChangeApplyPictureFacade storeStockChangeApplyPictureFacade;

	@Override
	public StoreStockChangeApplyPictureDTO findStoreStockChangeApplyPictureById(StoreStockChangeApplyPictureDTO dto) {
		return storeStockChangeApplyPictureFacade.findStoreStockChangeApplyPictureById(dto);
	}

	@Override
	public PageResult<StoreStockChangeApplyPictureDTO> findStoreStockChangeApplyPictureOfPage(StoreStockChangeApplyPictureDTO dto, Pagination page) {
		return storeStockChangeApplyPictureFacade.findStoreStockChangeApplyPictureOfPage(dto, page);
	}

	@Override
	public List<String> findStoreStockChangeApplyPictureAll(StoreStockChangeApplyPictureDTO dto) {
		List<StoreStockChangeApplyPictureDTO> list = storeStockChangeApplyPictureFacade.findStoreStockChangeApplyPictureAll(dto);
		List<String> pictures = new ArrayList<>(list.size());
		for (StoreStockChangeApplyPictureDTO storeStockChangeApplyPictureDTO : list) {
			pictures.add(storeStockChangeApplyPictureDTO.getPicturePath());
		}
		return pictures;
	}

	@Override
	public Long insertStoreStockChangeApplyPictureWithTx(StoreStockChangeApplyPictureDTO dto) {
		return storeStockChangeApplyPictureFacade.insertStoreStockChangeApplyPictureWithTx(dto);
	}

	@Override
	public int updateStoreStockChangeApplyPictureWithTx(StoreStockChangeApplyPictureDTO dto) {
		return storeStockChangeApplyPictureFacade.updateStoreStockChangeApplyPictureWithTx(dto);
	}

	@Override
	public int deleteStoreStockChangeApplyPictureWithTx(StoreStockChangeApplyPictureDTO dto) {
		return storeStockChangeApplyPictureFacade.deleteStoreStockChangeApplyPictureWithTx(dto);
	}


}
	