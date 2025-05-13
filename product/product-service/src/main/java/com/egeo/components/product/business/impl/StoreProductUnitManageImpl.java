package com.egeo.components.product.business.impl;
	

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.StoreProductUnitManage;
import com.egeo.components.product.facade.StoreProductUnitFacade;
import com.egeo.components.product.dto.StoreProductUnitDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("storeProductUnit")
public class StoreProductUnitManageImpl implements StoreProductUnitManage{

	
	@Resource(name="storeProductUnitFacade")
	private StoreProductUnitFacade storeProductUnitFacade;

	@Override
	public StoreProductUnitDTO findStoreProductUnitById(StoreProductUnitDTO dto) {
		return storeProductUnitFacade.findStoreProductUnitById(dto);
	}

	@Override
	public PageResult<Map<String, Object>> findStoreProductUnitOfPage(StoreProductUnitDTO dto, Pagination page) {
		return storeProductUnitFacade.findStoreProductUnitOfPage(dto, page);
	}

	@Override
	public List<StoreProductUnitDTO> findStoreProductUnitAll(StoreProductUnitDTO dto) {
		return storeProductUnitFacade.findStoreProductUnitAll(dto);
	}

	@Override
	public Long insertStoreProductUnitWithTx(StoreProductUnitDTO dto) {
		return storeProductUnitFacade.insertStoreProductUnitWithTx(dto);
	}

	@Override
	public int updateStoreProductUnitWithTx(StoreProductUnitDTO dto) {
		return storeProductUnitFacade.updateStoreProductUnitWithTx(dto);
	}

	@Override
	public int deleteStoreProductUnitWithTx(StoreProductUnitDTO dto) {
		return storeProductUnitFacade.deleteStoreProductUnitWithTx(dto);
	}


}
	