package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.StoreMenuTreeWriteService;
import com.egeo.components.product.manage.write.StoreMenuTreeWriteManage;
import com.egeo.components.product.converter.StoreMenuTreeConverter;
import com.egeo.components.product.dto.StoreMenuTreeDTO;
import com.egeo.components.product.po.StoreMenuTreePO;

@Service("storeMenuTreeWriteService")
public class StoreMenuTreeWriteServiceImpl  implements StoreMenuTreeWriteService {
	@Autowired
	private StoreMenuTreeWriteManage storeMenuTreeWriteManage;

	@Override
	public Long insertStoreMenuTreeWithTx(StoreMenuTreeDTO dto) {
		StoreMenuTreePO po = StoreMenuTreeConverter.toPO(dto);
		Long rt = storeMenuTreeWriteManage.insertStoreMenuTreeWithTx(po);		
		return rt;
	}

	@Override
	public int updateStoreMenuTreeWithTx(StoreMenuTreeDTO dto) {
		StoreMenuTreePO po = StoreMenuTreeConverter.toPO(dto);
		int rt = storeMenuTreeWriteManage.updateStoreMenuTreeWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStoreMenuTreeWithTx(StoreMenuTreeDTO dto) {
		StoreMenuTreePO po = StoreMenuTreeConverter.toPO(dto);
		int rt = storeMenuTreeWriteManage.deleteStoreMenuTreeWithTx(po);		
		return rt;
	}
}
	