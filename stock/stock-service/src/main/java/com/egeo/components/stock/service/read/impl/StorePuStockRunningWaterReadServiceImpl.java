package com.egeo.components.stock.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.service.read.StorePuStockRunningWaterReadService;
import com.egeo.components.stock.manage.read.StorePuStockRunningWaterReadManage;
import com.egeo.components.stock.condition.StorePuStockRunningWaterCondition;
import com.egeo.components.stock.converter.StorePuStockRunningWaterConverter;
import com.egeo.components.stock.dto.StorePuStockRunningWaterDTO;
import com.egeo.components.stock.po.StorePuStockRunningWaterPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("storePuStockRunningWaterReadService")
public class StorePuStockRunningWaterReadServiceImpl  implements StorePuStockRunningWaterReadService {
	@Autowired
	private StorePuStockRunningWaterReadManage storePuStockRunningWaterReadManage;

	@Override
	public StorePuStockRunningWaterDTO findStorePuStockRunningWaterById(StorePuStockRunningWaterDTO dto) {
		StorePuStockRunningWaterPO po = StorePuStockRunningWaterConverter.toPO(dto);
		StorePuStockRunningWaterPO list = storePuStockRunningWaterReadManage.findStorePuStockRunningWaterById(po);		
		return StorePuStockRunningWaterConverter.toDTO(list);
	}

	@Override
	public PageResult<StorePuStockRunningWaterDTO> findStorePuStockRunningWaterOfPage(StorePuStockRunningWaterDTO dto, Pagination page) {
		StorePuStockRunningWaterPO po = StorePuStockRunningWaterConverter.toPO(dto);
        PageResult<StorePuStockRunningWaterCondition> pageResult = storePuStockRunningWaterReadManage.findStorePuStockRunningWaterOfPage(po, page);
        
        List<StorePuStockRunningWaterDTO> list = StorePuStockRunningWaterConverter.conditionToDTO(pageResult.getList());
        PageResult<StorePuStockRunningWaterDTO> result = new PageResult<StorePuStockRunningWaterDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StorePuStockRunningWaterDTO> findStorePuStockRunningWaterAll(StorePuStockRunningWaterDTO dto) {
		StorePuStockRunningWaterPO po = StorePuStockRunningWaterConverter.toPO(dto);
		List<StorePuStockRunningWaterPO> list = storePuStockRunningWaterReadManage.findStorePuStockRunningWaterAll(po);		
		return StorePuStockRunningWaterConverter.toDTO(list);
	}
}
	