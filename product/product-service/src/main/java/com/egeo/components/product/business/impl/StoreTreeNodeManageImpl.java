package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.StoreTreeNodeManage;
import com.egeo.components.product.facade.StoreTreeNodeFacade;
import com.egeo.components.product.dto.StoreDTO;
import com.egeo.components.product.dto.StoreTreeNodeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("storeTreeNode")
public class StoreTreeNodeManageImpl implements StoreTreeNodeManage{

	
	@Resource(name="storeTreeNodeFacade")
	private StoreTreeNodeFacade storeTreeNodeFacade;

	@Override
	public StoreTreeNodeDTO findStoreTreeNodeById(StoreTreeNodeDTO dto) {
		return storeTreeNodeFacade.findStoreTreeNodeById(dto);
	}

	@Override
	public PageResult<StoreTreeNodeDTO> findStoreTreeNodeOfPage(StoreTreeNodeDTO dto, Pagination page) {
		return storeTreeNodeFacade.findStoreTreeNodeOfPage(dto, page);
	}

	@Override
	public List<StoreTreeNodeDTO> findStoreTreeNodeAll(StoreTreeNodeDTO dto) {
		return storeTreeNodeFacade.findStoreTreeNodeAll(dto);
	}

	@Override
	public Long insertStoreTreeNodeWithTx(StoreTreeNodeDTO storeTreeNodedto,StoreDTO storedto) {
		return storeTreeNodeFacade.insertStoreTreeNodeWithTx(storeTreeNodedto,storedto);
	}

	@Override
	public int updateStoreTreeNodeWithTx(StoreTreeNodeDTO dto) {
		return storeTreeNodeFacade.updateStoreTreeNodeWithTx(dto);
	}

	@Override
	public int deleteStoreTreeNodeWithTx(StoreTreeNodeDTO dto) {
		return storeTreeNodeFacade.deleteStoreTreeNodeWithTx(dto);
	}


}
	