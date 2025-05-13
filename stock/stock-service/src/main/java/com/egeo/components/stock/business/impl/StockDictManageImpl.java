package com.egeo.components.stock.business.impl;
	

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.business.StockDictManage;
import com.egeo.components.stock.facade.StockDictFacade;
import com.egeo.components.stock.dto.StockDictDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("stockDict")
public class StockDictManageImpl implements StockDictManage{

	
	@Resource(name="stockDictFacade")
	private StockDictFacade stockDictFacade;

	@Override
	public StockDictDTO findStockDictById(StockDictDTO dto) {
		return stockDictFacade.findStockDictById(dto);
	}

	@Override
	public PageResult<StockDictDTO> findStockDictOfPage(StockDictDTO dto, Pagination page) {
		return stockDictFacade.findStockDictOfPage(dto, page);
	}

	@Override
	public List<Map<String, Object>> findStockDictAll(StockDictDTO dto) {
		List<StockDictDTO> stockDictList = stockDictFacade.findStockDictAll(dto);
		List<Map<String, Object>> list = new ArrayList<>(stockDictList.size());
		for (StockDictDTO stockDictDTO : stockDictList) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", stockDictDTO.getCustomId());
			map.put("name", stockDictDTO.getName());
			list.add(map);
		}
		return list;
	}

	@Override
	public Long insertStockDictWithTx(StockDictDTO dto) {
		return stockDictFacade.insertStockDictWithTx(dto);
	}

	@Override
	public int updateStockDictWithTx(StockDictDTO dto) {
		return stockDictFacade.updateStockDictWithTx(dto);
	}

	@Override
	public int deleteStockDictWithTx(StockDictDTO dto) {
		return stockDictFacade.deleteStockDictWithTx(dto);
	}


}
	