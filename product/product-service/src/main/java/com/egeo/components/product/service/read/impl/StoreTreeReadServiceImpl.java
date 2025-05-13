package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.StoreTreeReadService;
import com.egeo.components.product.manage.read.StoreTreeReadManage;
import com.egeo.components.product.converter.StoreTreeConverter;
import com.egeo.components.product.dto.StoreTreeDTO;
import com.egeo.components.product.po.StoreTreePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("storeTreeReadService")
public class StoreTreeReadServiceImpl  implements StoreTreeReadService {
	@Autowired
	private StoreTreeReadManage storeTreeReadManage;

	@Override
	public StoreTreeDTO findStoreTreeById(StoreTreeDTO dto) {
		StoreTreePO po = StoreTreeConverter.toPO(dto);
		StoreTreePO list = storeTreeReadManage.findStoreTreeById(po);		
		return StoreTreeConverter.toDTO(list);
	}

	@Override
	public PageResult<StoreTreeDTO> findStoreTreeOfPage(StoreTreeDTO dto, Pagination page) {
		StoreTreePO po = StoreTreeConverter.toPO(dto);
        PageResult<StoreTreePO> pageResult = storeTreeReadManage.findStoreTreeOfPage(po, page);
        
        List<StoreTreeDTO> list = StoreTreeConverter.toDTO(pageResult.getList());
        PageResult<StoreTreeDTO> result = new PageResult<StoreTreeDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StoreTreeDTO> findStoreTreeAll(StoreTreeDTO dto) {
		StoreTreePO po = StoreTreeConverter.toPO(dto);
		List<StoreTreePO> list = storeTreeReadManage.findStoreTreeAll(po);		
		return StoreTreeConverter.toDTO(list);
	}
}
	