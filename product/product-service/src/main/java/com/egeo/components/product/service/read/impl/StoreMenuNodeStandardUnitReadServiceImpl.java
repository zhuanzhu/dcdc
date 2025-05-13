package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.StoreMenuNodeStandardUnitReadService;
import com.egeo.components.product.manage.read.StoreMenuNodeStandardUnitReadManage;
import com.egeo.components.product.converter.StoreMenuNodeStandardUnitConverter;
import com.egeo.components.product.dto.StoreMenuNodeStandardUnitDTO;
import com.egeo.components.product.po.StoreMenuNodeStandardUnitPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("storeMenuNodeStandardUnitReadService")
public class StoreMenuNodeStandardUnitReadServiceImpl  implements StoreMenuNodeStandardUnitReadService {
	@Autowired
	private StoreMenuNodeStandardUnitReadManage storeMenuNodeStandardUnitReadManage;

	@Override
	public StoreMenuNodeStandardUnitDTO findStoreMenuNodeStandardUnitById(StoreMenuNodeStandardUnitDTO dto) {
		StoreMenuNodeStandardUnitPO po = StoreMenuNodeStandardUnitConverter.toPO(dto);
		StoreMenuNodeStandardUnitPO list = storeMenuNodeStandardUnitReadManage.findStoreMenuNodeStandardUnitById(po);		
		return StoreMenuNodeStandardUnitConverter.toDTO(list);
	}

	@Override
	public PageResult<StoreMenuNodeStandardUnitDTO> findStoreMenuNodeStandardUnitOfPage(StoreMenuNodeStandardUnitDTO dto, Pagination page) {
		StoreMenuNodeStandardUnitPO po = StoreMenuNodeStandardUnitConverter.toPO(dto);
        PageResult<StoreMenuNodeStandardUnitPO> pageResult = storeMenuNodeStandardUnitReadManage.findStoreMenuNodeStandardUnitOfPage(po, page);
        
        List<StoreMenuNodeStandardUnitDTO> list = StoreMenuNodeStandardUnitConverter.toDTO(pageResult.getList());
        PageResult<StoreMenuNodeStandardUnitDTO> result = new PageResult<StoreMenuNodeStandardUnitDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StoreMenuNodeStandardUnitDTO> findStoreMenuNodeStandardUnitAll(StoreMenuNodeStandardUnitDTO dto) {
		StoreMenuNodeStandardUnitPO po = StoreMenuNodeStandardUnitConverter.toPO(dto);
		List<StoreMenuNodeStandardUnitPO> list = storeMenuNodeStandardUnitReadManage.findStoreMenuNodeStandardUnitAll(po);		
		return StoreMenuNodeStandardUnitConverter.toDTO(list);
	}
}
	