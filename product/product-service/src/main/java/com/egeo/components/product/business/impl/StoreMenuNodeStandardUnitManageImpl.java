package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.StoreMenuNodeStandardUnitManage;
import com.egeo.components.product.facade.StoreMenuNodeStandardUnitFacade;
import com.egeo.components.product.dto.StoreMenuNodeStandardUnitDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("storeMenuNodeStandardUnit")
public class StoreMenuNodeStandardUnitManageImpl implements StoreMenuNodeStandardUnitManage{

	
	@Resource(name="storeMenuNodeStandardUnitFacade")
	private StoreMenuNodeStandardUnitFacade storeMenuNodeStandardUnitFacade;

	@Override
	public StoreMenuNodeStandardUnitDTO findStoreMenuNodeStandardUnitById(StoreMenuNodeStandardUnitDTO dto) {
		return storeMenuNodeStandardUnitFacade.findStoreMenuNodeStandardUnitById(dto);
	}

	@Override
	public PageResult<StoreMenuNodeStandardUnitDTO> findStoreMenuNodeStandardUnitOfPage(StoreMenuNodeStandardUnitDTO dto, Pagination page) {
		return storeMenuNodeStandardUnitFacade.findStoreMenuNodeStandardUnitOfPage(dto, page);
	}

	@Override
	public List<StoreMenuNodeStandardUnitDTO> findStoreMenuNodeStandardUnitAll(StoreMenuNodeStandardUnitDTO dto) {
		return storeMenuNodeStandardUnitFacade.findStoreMenuNodeStandardUnitAll(dto);
	}

	@Override
	public Long insertStoreMenuNodeStandardUnitWithTx(StoreMenuNodeStandardUnitDTO dto) {
		return storeMenuNodeStandardUnitFacade.insertStoreMenuNodeStandardUnitWithTx(dto);
	}

	@Override
	public int updateStoreMenuNodeStandardUnitWithTx(StoreMenuNodeStandardUnitDTO dto) {
		return storeMenuNodeStandardUnitFacade.updateStoreMenuNodeStandardUnitWithTx(dto);
	}

	@Override
	public int deleteStoreMenuNodeStandardUnitWithTx(StoreMenuNodeStandardUnitDTO dto) {
		return storeMenuNodeStandardUnitFacade.deleteStoreMenuNodeStandardUnitWithTx(dto);
	}

	@Override
	public int insertAllWithTx(Long storeMenuNodeId,List<Long> standardUnitIds, Long platformId) {
		return storeMenuNodeStandardUnitFacade.insertAllWithTx(storeMenuNodeId,standardUnitIds, platformId);
	}


}
	