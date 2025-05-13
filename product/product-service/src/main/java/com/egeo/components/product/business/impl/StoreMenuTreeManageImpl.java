package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.StoreMenuTreeManage;
import com.egeo.components.product.facade.StoreMenuTreeFacade;
import com.egeo.components.product.dto.StoreMenuTreeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("storeMenuTree")
public class StoreMenuTreeManageImpl implements StoreMenuTreeManage{

	
	@Resource(name="storeMenuTreeFacade")
	private StoreMenuTreeFacade storeMenuTreeFacade;

	@Override
	public StoreMenuTreeDTO findStoreMenuTreeById(StoreMenuTreeDTO dto) {
		return storeMenuTreeFacade.findStoreMenuTreeById(dto);
	}

	@Override
	public PageResult<StoreMenuTreeDTO> findStoreMenuTreeOfPage(StoreMenuTreeDTO dto, Pagination page) {
		return storeMenuTreeFacade.findStoreMenuTreeOfPage(dto, page);
	}

	@Override
	public List<StoreMenuTreeDTO> findStoreMenuTreeAll(StoreMenuTreeDTO dto) {
		return storeMenuTreeFacade.findStoreMenuTreeAll(dto);
	}

	@Override
	public Long insertStoreMenuTreeWithTx(StoreMenuTreeDTO dto) {
		return storeMenuTreeFacade.insertStoreMenuTreeWithTx(dto);
	}

	@Override
	public int updateStoreMenuTreeWithTx(StoreMenuTreeDTO dto) {
		return storeMenuTreeFacade.updateStoreMenuTreeWithTx(dto);
	}

	@Override
	public int deleteStoreMenuTreeWithTx(StoreMenuTreeDTO dto) {
		return storeMenuTreeFacade.deleteStoreMenuTreeWithTx(dto);
	}


}
	