package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.StoreMenuTreeReadService;
import com.egeo.components.product.manage.read.StoreMenuTreeReadManage;
import com.egeo.components.product.converter.StoreMenuTreeConverter;
import com.egeo.components.product.dto.StoreMenuTreeDTO;
import com.egeo.components.product.po.StoreMenuTreePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("storeMenuTreeReadService")
public class StoreMenuTreeReadServiceImpl  implements StoreMenuTreeReadService {
	@Autowired
	private StoreMenuTreeReadManage storeMenuTreeReadManage;

	@Override
	public StoreMenuTreeDTO findStoreMenuTreeById(StoreMenuTreeDTO dto) {
		StoreMenuTreePO po = StoreMenuTreeConverter.toPO(dto);
		StoreMenuTreePO list = storeMenuTreeReadManage.findStoreMenuTreeById(po);		
		return StoreMenuTreeConverter.toDTO(list);
	}

	@Override
	public PageResult<StoreMenuTreeDTO> findStoreMenuTreeOfPage(StoreMenuTreeDTO dto, Pagination page) {
		StoreMenuTreePO po = StoreMenuTreeConverter.toPO(dto);
        PageResult<StoreMenuTreePO> pageResult = storeMenuTreeReadManage.findStoreMenuTreeOfPage(po, page);
        
        List<StoreMenuTreeDTO> list = StoreMenuTreeConverter.toDTO(pageResult.getList());
        PageResult<StoreMenuTreeDTO> result = new PageResult<StoreMenuTreeDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StoreMenuTreeDTO> findStoreMenuTreeAll(StoreMenuTreeDTO dto) {
		StoreMenuTreePO po = StoreMenuTreeConverter.toPO(dto);
		List<StoreMenuTreePO> list = storeMenuTreeReadManage.findStoreMenuTreeAll(po);		
		return StoreMenuTreeConverter.toDTO(list);
	}
}
	