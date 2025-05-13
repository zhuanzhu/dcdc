package com.egeo.components.stock.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.service.read.StorePuWarehouseStockReadService;
import com.egeo.components.stock.manage.read.StorePuWarehouseStockReadManage;
import com.egeo.components.stock.converter.StorePuWarehouseStockConverter;
import com.egeo.components.stock.dto.StorePuWarehouseStockDTO;
import com.egeo.components.stock.po.StorePuWarehouseStockPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("storePuWarehouseStockReadService")
public class StorePuWarehouseStockReadServiceImpl  implements StorePuWarehouseStockReadService {
	@Autowired
	private StorePuWarehouseStockReadManage storePuWarehouseStockReadManage;

	@Override
	public StorePuWarehouseStockDTO findStorePuWarehouseStockById(StorePuWarehouseStockDTO dto) {
		StorePuWarehouseStockPO po = StorePuWarehouseStockConverter.toPO(dto);
		StorePuWarehouseStockPO list = storePuWarehouseStockReadManage.findStorePuWarehouseStockById(po);		
		return StorePuWarehouseStockConverter.toDTO(list);
	}

	@Override
	public PageResult<StorePuWarehouseStockDTO> findStorePuWarehouseStockOfPage(StorePuWarehouseStockDTO dto, Pagination page) {
		StorePuWarehouseStockPO po = StorePuWarehouseStockConverter.toPO(dto);
        PageResult<StorePuWarehouseStockPO> pageResult = storePuWarehouseStockReadManage.findStorePuWarehouseStockOfPage(po, page);
        
        List<StorePuWarehouseStockDTO> list = StorePuWarehouseStockConverter.toDTO(pageResult.getList());
        PageResult<StorePuWarehouseStockDTO> result = new PageResult<StorePuWarehouseStockDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StorePuWarehouseStockDTO> findStorePuWarehouseStockAll(StorePuWarehouseStockDTO dto) {
		StorePuWarehouseStockPO po = StorePuWarehouseStockConverter.toPO(dto);
		List<StorePuWarehouseStockPO> list = storePuWarehouseStockReadManage.findStorePuWarehouseStockAll(po);		
		return StorePuWarehouseStockConverter.toDTO(list);
	}

	@Override
	public StorePuWarehouseStockDTO findByStorePuId(Long storePuId) {
		StorePuWarehouseStockPO storePuWarehouseStockPO = storePuWarehouseStockReadManage.findByStorePuId(storePuId);
		return StorePuWarehouseStockConverter.toDTO(storePuWarehouseStockPO);
	}

	@Override
	public Integer findByStorePuSellOutSum(Long storeId, Long platformId) {
		return storePuWarehouseStockReadManage.findByStorePuSellOutSum(storeId, platformId);
	}

	@Override
	public Integer findPuSellOutSumStoreByPuIds(List<Long> storePuIds) {
		return storePuWarehouseStockReadManage.findPuSellOutSumStoreByPuIds(storePuIds);
	}

	@Override
	public Long realStockNumByStoreProductUnitId(Long storeProductUnitId, Long storeId) {
		return storePuWarehouseStockReadManage.realStockNumByStoreProductUnitId(storeProductUnitId, storeId);
	}
}
	