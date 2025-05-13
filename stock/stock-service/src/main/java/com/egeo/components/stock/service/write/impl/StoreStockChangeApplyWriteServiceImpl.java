package com.egeo.components.stock.service.write.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.service.write.StoreStockChangeApplyWriteService;
import com.egeo.components.stock.manage.write.StoreStockChangeApplyWriteManage;
import com.egeo.components.stock.converter.StoreStockChangeApplyConverter;
import com.egeo.components.stock.dto.StoreStockChangeApplyDTO;
import com.egeo.components.stock.po.StoreStockChangeApplyPO;


@Service("storeStockChangeApplyWriteService")
public class StoreStockChangeApplyWriteServiceImpl  implements StoreStockChangeApplyWriteService {
	@Autowired
	private StoreStockChangeApplyWriteManage storeStockChangeApplyWriteManage;

	@Override
	public Long insertStoreStockChangeApplyWithTx(StoreStockChangeApplyDTO dto,List<String> pictures) {
		StoreStockChangeApplyPO po = StoreStockChangeApplyConverter.toPO(dto);
		Long rt = storeStockChangeApplyWriteManage.insertStoreStockChangeApplyWithTx(po,pictures);		
		return rt;
	}

	@Override
	public int updateStoreStockChangeApplyWithTx(StoreStockChangeApplyDTO dto) {
		StoreStockChangeApplyPO po = StoreStockChangeApplyConverter.toPO(dto);
		int rt = storeStockChangeApplyWriteManage.updateStoreStockChangeApplyWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStoreStockChangeApplyWithTx(StoreStockChangeApplyDTO dto) {
		StoreStockChangeApplyPO po = StoreStockChangeApplyConverter.toPO(dto);
		int rt = storeStockChangeApplyWriteManage.deleteStoreStockChangeApplyWithTx(po);		
		return rt;
	}
}
	