package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.StoreTreeWriteService;
import com.egeo.components.product.manage.write.StoreTreeWriteManage;
import com.egeo.components.product.converter.StoreTreeConverter;
import com.egeo.components.product.dto.StoreTreeDTO;
import com.egeo.components.product.po.StoreTreePO;

@Service("storeTreeWriteService")
public class StoreTreeWriteServiceImpl  implements StoreTreeWriteService {
	@Autowired
	private StoreTreeWriteManage storeTreeWriteManage;

	@Override
	public Long insertStoreTreeWithTx(StoreTreeDTO dto) {
		StoreTreePO po = StoreTreeConverter.toPO(dto);
		Long rt = storeTreeWriteManage.insertStoreTreeWithTx(po);		
		return rt;
	}

	@Override
	public int updateStoreTreeWithTx(StoreTreeDTO dto) {
		StoreTreePO po = StoreTreeConverter.toPO(dto);
		int rt = storeTreeWriteManage.updateStoreTreeWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStoreTreeWithTx(StoreTreeDTO dto) {
		StoreTreePO po = StoreTreeConverter.toPO(dto);
		int rt = storeTreeWriteManage.deleteStoreTreeWithTx(po);		
		return rt;
	}
}
	