package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.StoreMenuNodeWriteService;
import com.egeo.components.product.manage.write.StoreMenuNodeWriteManage;
import com.egeo.components.product.converter.StoreMenuNodeConverter;
import com.egeo.components.product.dto.StoreMenuNodeDTO;
import com.egeo.components.product.po.StoreMenuNodePO;

@Service("storeMenuNodeWriteService")
public class StoreMenuNodeWriteServiceImpl  implements StoreMenuNodeWriteService {
	@Autowired
	private StoreMenuNodeWriteManage storeMenuNodeWriteManage;

	@Override
	public Long insertStoreMenuNodeWithTx(StoreMenuNodeDTO dto) {
		StoreMenuNodePO po = StoreMenuNodeConverter.toPO(dto);
		Long rt = storeMenuNodeWriteManage.insertStoreMenuNodeWithTx(po);		
		return rt;
	}

	@Override
	public int updateStoreMenuNodeWithTx(StoreMenuNodeDTO dto) {
		StoreMenuNodePO po = StoreMenuNodeConverter.toPO(dto);
		int rt = storeMenuNodeWriteManage.updateStoreMenuNodeWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStoreMenuNodeWithTx(StoreMenuNodeDTO dto) {
		StoreMenuNodePO po = StoreMenuNodeConverter.toPO(dto);
		int rt = storeMenuNodeWriteManage.deleteStoreMenuNodeWithTx(po);		
		return rt;
	}
}
	