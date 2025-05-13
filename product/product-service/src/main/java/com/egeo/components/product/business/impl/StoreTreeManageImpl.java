package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.StoreTreeManage;
import com.egeo.components.product.facade.StoreTreeFacade;
import com.egeo.components.product.dto.StoreTreeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("storeTree")
public class StoreTreeManageImpl implements StoreTreeManage{

	
	@Resource(name="storeTreeFacade")
	private StoreTreeFacade storeTreeFacade;

	@Override
	public StoreTreeDTO findStoreTreeById(StoreTreeDTO dto) {
		return storeTreeFacade.findStoreTreeById(dto);
	}

	@Override
	public PageResult<StoreTreeDTO> findStoreTreeOfPage(StoreTreeDTO dto, Pagination page) {
		return storeTreeFacade.findStoreTreeOfPage(dto, page);
	}

	@Override
	public List<StoreTreeDTO> findStoreTreeAll(StoreTreeDTO dto) {
		return storeTreeFacade.findStoreTreeAll(dto);
	}

	@Override
	public Long insertStoreTreeWithTx(StoreTreeDTO dto) {
		return storeTreeFacade.insertStoreTreeWithTx(dto);
	}

	@Override
	public int updateStoreTreeWithTx(StoreTreeDTO dto) {
		return storeTreeFacade.updateStoreTreeWithTx(dto);
	}

	@Override
	public int deleteStoreTreeWithTx(StoreTreeDTO dto) {
		return storeTreeFacade.deleteStoreTreeWithTx(dto);
	}


}
	