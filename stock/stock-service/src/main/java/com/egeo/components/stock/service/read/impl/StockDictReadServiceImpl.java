package com.egeo.components.stock.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.service.read.StockDictReadService;
import com.egeo.components.stock.manage.read.StockDictReadManage;
import com.egeo.components.stock.converter.StockDictConverter;
import com.egeo.components.stock.dto.StockDictDTO;
import com.egeo.components.stock.po.StockDictPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("stockDictReadService")
public class StockDictReadServiceImpl  implements StockDictReadService {
	@Autowired
	private StockDictReadManage stockDictReadManage;

	@Override
	public StockDictDTO findStockDictById(StockDictDTO dto) {
		StockDictPO po = StockDictConverter.toPO(dto);
		StockDictPO list = stockDictReadManage.findStockDictById(po);		
		return StockDictConverter.toDTO(list);
	}

	@Override
	public PageResult<StockDictDTO> findStockDictOfPage(StockDictDTO dto, Pagination page) {
		StockDictPO po = StockDictConverter.toPO(dto);
        PageResult<StockDictPO> pageResult = stockDictReadManage.findStockDictOfPage(po, page);
        
        List<StockDictDTO> list = StockDictConverter.toDTO(pageResult.getList());
        PageResult<StockDictDTO> result = new PageResult<StockDictDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StockDictDTO> findStockDictAll(StockDictDTO dto) {
		StockDictPO po = StockDictConverter.toPO(dto);
		List<StockDictPO> list = stockDictReadManage.findStockDictAll(po);		
		return StockDictConverter.toDTO(list);
	}
}
	