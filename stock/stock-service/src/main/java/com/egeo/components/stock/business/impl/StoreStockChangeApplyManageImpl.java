package com.egeo.components.stock.business.impl;
	

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.business.StoreStockChangeApplyManage;
import com.egeo.components.stock.facade.StoreStockChangeApplyFacade;
import com.egeo.components.stock.dto.StoreStockChangeApplyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("storeStockChangeApply")
public class StoreStockChangeApplyManageImpl implements StoreStockChangeApplyManage{

	
	@Resource(name="storeStockChangeApplyFacade")
	private StoreStockChangeApplyFacade storeStockChangeApplyFacade;

	@Override
	public StoreStockChangeApplyDTO findStoreStockChangeApplyById(StoreStockChangeApplyDTO dto) {
		return storeStockChangeApplyFacade.findStoreStockChangeApplyById(dto);
	}

	@Override
	public PageResult<StoreStockChangeApplyDTO> findStoreStockChangeApplyOfPage(StoreStockChangeApplyDTO dto, Pagination page) {
		return storeStockChangeApplyFacade.findStoreStockChangeApplyOfPage(dto, page);
	}

	@Override
	public List<StoreStockChangeApplyDTO> findStoreStockChangeApplyAll(StoreStockChangeApplyDTO dto) {
		return storeStockChangeApplyFacade.findStoreStockChangeApplyAll(dto);
	}

	@Override
	public Long insertStoreStockChangeApplyWithTx(StoreStockChangeApplyDTO dto,List<String> pictures) {
		return storeStockChangeApplyFacade.insertStoreStockChangeApplyWithTx(dto,pictures);
	}

	@Override
	public int updateStoreStockChangeApplyWithTx(StoreStockChangeApplyDTO dto) {
		return storeStockChangeApplyFacade.updateStoreStockChangeApplyWithTx(dto);
	}

	@Override
	public int deleteStoreStockChangeApplyWithTx(StoreStockChangeApplyDTO dto) {
		return storeStockChangeApplyFacade.deleteStoreStockChangeApplyWithTx(dto);
	}

	@Override
	public Map<String, Object> findStoreStockChangeApplyOfPageAPP(StoreStockChangeApplyDTO dto, Pagination page) {
		return storeStockChangeApplyFacade.findStoreStockChangeApplyOfPageAPP(dto, page);
	}


}
	