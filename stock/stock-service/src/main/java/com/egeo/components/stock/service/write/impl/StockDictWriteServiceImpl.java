package com.egeo.components.stock.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.stock.service.write.StockDictWriteService;
import com.egeo.components.stock.manage.write.StockDictWriteManage;
import com.egeo.components.stock.converter.StockDictConverter;
import com.egeo.components.stock.dto.StockDictDTO;
import com.egeo.components.stock.po.StockDictPO;

@Service("stockDictWriteService")
public class StockDictWriteServiceImpl  implements StockDictWriteService {
	@Autowired
	private StockDictWriteManage stockDictWriteManage;

	@Override
	public Long insertStockDictWithTx(StockDictDTO dto) {
		StockDictPO po = StockDictConverter.toPO(dto);
		Long rt = stockDictWriteManage.insertStockDictWithTx(po);		
		return rt;
	}

	@Override
	public int updateStockDictWithTx(StockDictDTO dto) {
		StockDictPO po = StockDictConverter.toPO(dto);
		int rt = stockDictWriteManage.updateStockDictWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStockDictWithTx(StockDictDTO dto) {
		StockDictPO po = StockDictConverter.toPO(dto);
		int rt = stockDictWriteManage.deleteStockDictWithTx(po);		
		return rt;
	}
}
	