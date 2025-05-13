package com.egeo.components.product.service.write.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.StoreMenuNodeStandardUnitWriteService;
import com.egeo.components.product.manage.write.StoreMenuNodeStandardUnitWriteManage;
import com.egeo.components.product.converter.StoreMenuNodeStandardUnitConverter;
import com.egeo.components.product.dto.StoreMenuNodeStandardUnitDTO;
import com.egeo.components.product.po.StoreMenuNodeStandardUnitPO;

@Service("storeMenuNodeStandardUnitWriteService")
public class StoreMenuNodeStandardUnitWriteServiceImpl  implements StoreMenuNodeStandardUnitWriteService {
	@Autowired
	private StoreMenuNodeStandardUnitWriteManage storeMenuNodeStandardUnitWriteManage;

	@Override
	public Long insertStoreMenuNodeStandardUnitWithTx(StoreMenuNodeStandardUnitDTO dto) {
		StoreMenuNodeStandardUnitPO po = StoreMenuNodeStandardUnitConverter.toPO(dto);
		Long rt = storeMenuNodeStandardUnitWriteManage.insertStoreMenuNodeStandardUnitWithTx(po);		
		return rt;
	}

	@Override
	public int updateStoreMenuNodeStandardUnitWithTx(StoreMenuNodeStandardUnitDTO dto) {
		StoreMenuNodeStandardUnitPO po = StoreMenuNodeStandardUnitConverter.toPO(dto);
		int rt = storeMenuNodeStandardUnitWriteManage.updateStoreMenuNodeStandardUnitWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStoreMenuNodeStandardUnitWithTx(StoreMenuNodeStandardUnitDTO dto) {
		StoreMenuNodeStandardUnitPO po = StoreMenuNodeStandardUnitConverter.toPO(dto);
		int rt = storeMenuNodeStandardUnitWriteManage.deleteStoreMenuNodeStandardUnitWithTx(po);		
		return rt;
	}

	@Override
	public int insertAllWithTx(Long storeMenuNodeId,List<Long> standardUnitIds, Long platformId) {
		return storeMenuNodeStandardUnitWriteManage.insertAllWithTx(storeMenuNodeId,standardUnitIds, platformId);
	}
}
	