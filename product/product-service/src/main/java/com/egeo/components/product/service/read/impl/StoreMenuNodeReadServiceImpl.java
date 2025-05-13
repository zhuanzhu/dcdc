package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.StoreMenuNodeReadService;
import com.egeo.components.product.manage.read.StoreMenuNodeReadManage;
import com.egeo.components.product.converter.StoreMenuNodeConverter;
import com.egeo.components.product.dto.StoreMenuNodeDTO;
import com.egeo.components.product.po.StoreMenuNodePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("storeMenuNodeReadService")
public class StoreMenuNodeReadServiceImpl  implements StoreMenuNodeReadService {
	@Autowired
	private StoreMenuNodeReadManage storeMenuNodeReadManage;

	@Override
	public StoreMenuNodeDTO findStoreMenuNodeById(StoreMenuNodeDTO dto) {
		StoreMenuNodePO po = StoreMenuNodeConverter.toPO(dto);
		StoreMenuNodePO list = storeMenuNodeReadManage.findStoreMenuNodeById(po);		
		return StoreMenuNodeConverter.toDTO(list);
	}

	@Override
	public PageResult<StoreMenuNodeDTO> findStoreMenuNodeOfPage(StoreMenuNodeDTO dto, Pagination page) {
		StoreMenuNodePO po = StoreMenuNodeConverter.toPO(dto);
        PageResult<StoreMenuNodePO> pageResult = storeMenuNodeReadManage.findStoreMenuNodeOfPage(po, page);
        
        List<StoreMenuNodeDTO> list = StoreMenuNodeConverter.toDTO(pageResult.getList());
        PageResult<StoreMenuNodeDTO> result = new PageResult<StoreMenuNodeDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StoreMenuNodeDTO> findStoreMenuNodeAll(StoreMenuNodeDTO dto) {
		StoreMenuNodePO po = StoreMenuNodeConverter.toPO(dto);
		List<StoreMenuNodePO> list = storeMenuNodeReadManage.findStoreMenuNodeAll(po);		
		return StoreMenuNodeConverter.toDTO(list);
	}
}
	