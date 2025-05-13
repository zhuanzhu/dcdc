package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.StoreProductUnitReadService;
import com.egeo.components.product.manage.read.StoreProductUnitReadManage;
import com.egeo.components.product.converter.StoreProductUnitConverter;
import com.egeo.components.product.dto.StoreProductUnitDTO;
import com.egeo.components.product.po.StoreProductUnitPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("storeProductUnitReadService")
public class StoreProductUnitReadServiceImpl  implements StoreProductUnitReadService {
	@Autowired
	private StoreProductUnitReadManage storeProductUnitReadManage;

	@Override
	public StoreProductUnitDTO findStoreProductUnitById(StoreProductUnitDTO dto) {
		StoreProductUnitPO po = StoreProductUnitConverter.toPO(dto);
		StoreProductUnitPO list = storeProductUnitReadManage.findStoreProductUnitById(po);		
		return StoreProductUnitConverter.toDTO(list);
	}

	@Override
	public PageResult<StoreProductUnitDTO> findStoreProductUnitOfPage(StoreProductUnitDTO dto, Pagination page) {
		StoreProductUnitPO po = StoreProductUnitConverter.toPO(dto);
        PageResult<StoreProductUnitPO> pageResult = storeProductUnitReadManage.findStoreProductUnitOfPage(po, page);
        
        List<StoreProductUnitDTO> list = StoreProductUnitConverter.toDTO(pageResult.getList());
        PageResult<StoreProductUnitDTO> result = new PageResult<StoreProductUnitDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StoreProductUnitDTO> findStoreProductUnitAll(StoreProductUnitDTO dto) {
		StoreProductUnitPO po = StoreProductUnitConverter.toPO(dto);
		List<StoreProductUnitPO> list = storeProductUnitReadManage.findStoreProductUnitAll(po);		
		return StoreProductUnitConverter.toDTO(list);
	}
	
	@Override
	public List<Long> findStorePuIdsByStoreId(Long storeId, Long platformId) {
		return storeProductUnitReadManage.findStorePuIdsByStoreId(storeId, platformId);
	}
}
	