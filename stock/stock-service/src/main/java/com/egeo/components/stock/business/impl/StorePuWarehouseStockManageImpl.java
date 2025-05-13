package com.egeo.components.stock.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.business.StorePuWarehouseStockManage;
import com.egeo.components.stock.facade.StorePuWarehouseStockFacade;
import com.egeo.components.stock.dto.StorePuWarehouseStockDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("storePuWarehouseStock")
public class StorePuWarehouseStockManageImpl implements StorePuWarehouseStockManage{

	
	@Resource(name="storePuWarehouseStockFacade")
	private StorePuWarehouseStockFacade storePuWarehouseStockFacade;

	@Override
	public StorePuWarehouseStockDTO findStorePuWarehouseStockById(StorePuWarehouseStockDTO dto) {
		return storePuWarehouseStockFacade.findStorePuWarehouseStockById(dto);
	}

	@Override
	public PageResult<StorePuWarehouseStockDTO> findStorePuWarehouseStockOfPage(StorePuWarehouseStockDTO dto, Pagination page) {
		return storePuWarehouseStockFacade.findStorePuWarehouseStockOfPage(dto, page);
	}

	@Override
	public List<StorePuWarehouseStockDTO> findStorePuWarehouseStockAll(StorePuWarehouseStockDTO dto) {
		return storePuWarehouseStockFacade.findStorePuWarehouseStockAll(dto);
	}

	@Override
	public Long insertStorePuWarehouseStockWithTx(StorePuWarehouseStockDTO dto) {
		return storePuWarehouseStockFacade.insertStorePuWarehouseStockWithTx(dto);
	}

	@Override
	public int updateStorePuWarehouseStockWithTx(StorePuWarehouseStockDTO dto) {
		return storePuWarehouseStockFacade.updateStorePuWarehouseStockWithTx(dto);
	}

	@Override
	public int deleteStorePuWarehouseStockWithTx(StorePuWarehouseStockDTO dto) {
		return storePuWarehouseStockFacade.deleteStorePuWarehouseStockWithTx(dto);
	}


}
	