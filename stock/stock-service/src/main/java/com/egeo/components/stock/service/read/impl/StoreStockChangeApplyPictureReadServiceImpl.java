package com.egeo.components.stock.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.service.read.StoreStockChangeApplyPictureReadService;
import com.egeo.components.stock.manage.read.StoreStockChangeApplyPictureReadManage;
import com.egeo.components.stock.converter.StoreStockChangeApplyPictureConverter;
import com.egeo.components.stock.dto.StoreStockChangeApplyPictureDTO;
import com.egeo.components.stock.po.StoreStockChangeApplyPicturePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("storeStockChangeApplyPictureReadService")
public class StoreStockChangeApplyPictureReadServiceImpl  implements StoreStockChangeApplyPictureReadService {
	@Autowired
	private StoreStockChangeApplyPictureReadManage storeStockChangeApplyPictureReadManage;

	@Override
	public StoreStockChangeApplyPictureDTO findStoreStockChangeApplyPictureById(StoreStockChangeApplyPictureDTO dto) {
		StoreStockChangeApplyPicturePO po = StoreStockChangeApplyPictureConverter.toPO(dto);
		StoreStockChangeApplyPicturePO list = storeStockChangeApplyPictureReadManage.findStoreStockChangeApplyPictureById(po);		
		return StoreStockChangeApplyPictureConverter.toDTO(list);
	}

	@Override
	public PageResult<StoreStockChangeApplyPictureDTO> findStoreStockChangeApplyPictureOfPage(StoreStockChangeApplyPictureDTO dto, Pagination page) {
		StoreStockChangeApplyPicturePO po = StoreStockChangeApplyPictureConverter.toPO(dto);
        PageResult<StoreStockChangeApplyPicturePO> pageResult = storeStockChangeApplyPictureReadManage.findStoreStockChangeApplyPictureOfPage(po, page);
        
        List<StoreStockChangeApplyPictureDTO> list = StoreStockChangeApplyPictureConverter.toDTO(pageResult.getList());
        PageResult<StoreStockChangeApplyPictureDTO> result = new PageResult<StoreStockChangeApplyPictureDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StoreStockChangeApplyPictureDTO> findStoreStockChangeApplyPictureAll(StoreStockChangeApplyPictureDTO dto) {
		StoreStockChangeApplyPicturePO po = StoreStockChangeApplyPictureConverter.toPO(dto);
		List<StoreStockChangeApplyPicturePO> list = storeStockChangeApplyPictureReadManage.findStoreStockChangeApplyPictureAll(po);		
		return StoreStockChangeApplyPictureConverter.toDTO(list);
	}
}
	