package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.StoreTreeNodeReadService;
import com.egeo.components.product.manage.read.StoreTreeNodeReadManage;
import com.egeo.components.product.converter.StoreTreeNodeConverter;
import com.egeo.components.product.dto.StoreTreeNodeDTO;
import com.egeo.components.product.po.StoreTreeNodePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("storeTreeNodeReadService")
public class StoreTreeNodeReadServiceImpl  implements StoreTreeNodeReadService {
	@Autowired
	private StoreTreeNodeReadManage storeTreeNodeReadManage;

	@Override
	public StoreTreeNodeDTO findStoreTreeNodeById(StoreTreeNodeDTO dto) {
		StoreTreeNodePO po = StoreTreeNodeConverter.toPO(dto);
		StoreTreeNodePO list = storeTreeNodeReadManage.findStoreTreeNodeById(po);		
		return StoreTreeNodeConverter.toDTO(list);
	}

	@Override
	public PageResult<StoreTreeNodeDTO> findStoreTreeNodeOfPage(StoreTreeNodeDTO dto, Pagination page) {
		StoreTreeNodePO po = StoreTreeNodeConverter.toPO(dto);
        PageResult<StoreTreeNodePO> pageResult = storeTreeNodeReadManage.findStoreTreeNodeOfPage(po, page);
        
        List<StoreTreeNodeDTO> list = StoreTreeNodeConverter.toDTO(pageResult.getList());
        PageResult<StoreTreeNodeDTO> result = new PageResult<StoreTreeNodeDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StoreTreeNodeDTO> findStoreTreeNodeAll(StoreTreeNodeDTO dto) {
		StoreTreeNodePO po = StoreTreeNodeConverter.toPO(dto);
		List<StoreTreeNodePO> list = storeTreeNodeReadManage.findStoreTreeNodeAll(po);		
		return StoreTreeNodeConverter.toDTO(list);
	}

	@Override
	public boolean findHeadStoreByStoreId(Long storeId) {
		return storeTreeNodeReadManage.findHeadStoreByStoreId(storeId);
	}
}
	