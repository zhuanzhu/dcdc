package com.egeo.components.stock.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.service.read.StoreStockChangeApplyReadService;
import com.egeo.components.stock.manage.read.StoreStockChangeApplyReadManage;
import com.egeo.components.stock.converter.StoreStockChangeApplyConverter;
import com.egeo.components.stock.dto.StoreStockChangeApplyDTO;
import com.egeo.components.stock.po.StoreStockChangeApplyPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("storeStockChangeApplyReadService")
public class StoreStockChangeApplyReadServiceImpl  implements StoreStockChangeApplyReadService {
	@Autowired
	private StoreStockChangeApplyReadManage storeStockChangeApplyReadManage;

	@Override
	public StoreStockChangeApplyDTO findStoreStockChangeApplyById(StoreStockChangeApplyDTO dto) {
		StoreStockChangeApplyPO po = StoreStockChangeApplyConverter.toPO(dto);
		StoreStockChangeApplyPO list = storeStockChangeApplyReadManage.findStoreStockChangeApplyById(po);		
		return StoreStockChangeApplyConverter.toDTO(list);
	}

	@Override
	public PageResult<StoreStockChangeApplyDTO> findStoreStockChangeApplyOfPage(StoreStockChangeApplyDTO dto, Pagination page) {
		StoreStockChangeApplyPO po = StoreStockChangeApplyConverter.toPO(dto);
        PageResult<StoreStockChangeApplyPO> pageResult = storeStockChangeApplyReadManage.findStoreStockChangeApplyOfPage(po, page);
        
        List<StoreStockChangeApplyDTO> list = StoreStockChangeApplyConverter.toDTO(pageResult.getList());
        PageResult<StoreStockChangeApplyDTO> result = new PageResult<StoreStockChangeApplyDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StoreStockChangeApplyDTO> findStoreStockChangeApplyAll(StoreStockChangeApplyDTO dto) {
		StoreStockChangeApplyPO po = StoreStockChangeApplyConverter.toPO(dto);
		List<StoreStockChangeApplyPO> list = storeStockChangeApplyReadManage.findStoreStockChangeApplyAll(po);		
		return StoreStockChangeApplyConverter.toDTO(list);
	}
}
	