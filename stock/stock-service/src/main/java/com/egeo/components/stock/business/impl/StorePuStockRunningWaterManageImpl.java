package com.egeo.components.stock.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.business.StorePuStockRunningWaterManage;
import com.egeo.components.stock.facade.StorePuStockRunningWaterFacade;
import com.egeo.components.stock.dto.StorePuStockRunningWaterDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("storePuStockRunningWater")
public class StorePuStockRunningWaterManageImpl implements StorePuStockRunningWaterManage{

	
	@Resource(name="storePuStockRunningWaterFacade")
	private StorePuStockRunningWaterFacade storePuStockRunningWaterFacade;

	@Override
	public StorePuStockRunningWaterDTO findStorePuStockRunningWaterById(StorePuStockRunningWaterDTO dto) {
		return storePuStockRunningWaterFacade.findStorePuStockRunningWaterById(dto);
	}

	@Override
	public PageResult<StorePuStockRunningWaterDTO> findStorePuStockRunningWaterOfPage(StorePuStockRunningWaterDTO dto, Long storeId, Pagination page) {
		return storePuStockRunningWaterFacade.findStorePuStockRunningWaterOfPage(dto, storeId, page);
	}

	@Override
	public List<StorePuStockRunningWaterDTO> findStorePuStockRunningWaterAll(StorePuStockRunningWaterDTO dto) {
		return storePuStockRunningWaterFacade.findStorePuStockRunningWaterAll(dto);
	}

	@Override
	public Long insertStorePuStockRunningWaterWithTx(StorePuStockRunningWaterDTO dto) {
		return storePuStockRunningWaterFacade.insertStorePuStockRunningWaterWithTx(dto);
	}

	@Override
	public int updateStorePuStockRunningWaterWithTx(StorePuStockRunningWaterDTO dto) {
		return storePuStockRunningWaterFacade.updateStorePuStockRunningWaterWithTx(dto);
	}

	@Override
	public int deleteStorePuStockRunningWaterWithTx(StorePuStockRunningWaterDTO dto) {
		return storePuStockRunningWaterFacade.deleteStorePuStockRunningWaterWithTx(dto);
	}


}
	