package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.StoreWriteService;
import com.egeo.components.product.manage.write.StoreWriteManage;
import com.egeo.components.product.converter.StoreConverter;
import com.egeo.components.product.dto.StoreDTO;
import com.egeo.components.product.po.StorePO;

@Service("storeWriteService")
public class StoreWriteServiceImpl  implements StoreWriteService {
	@Autowired
	private StoreWriteManage storeWriteManage;

	@Override
	public Long insertStoreWithTx(StoreDTO dto) {
		StorePO po = StoreConverter.toPO(dto);
		Long rt = storeWriteManage.insertStoreWithTx(po);		
		return rt;
	}

	@Override
	public int updateStoreWithTx(StoreDTO dto) {
		StorePO po = StoreConverter.toPO(dto);
		int rt = storeWriteManage.updateStoreWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStoreWithTx(StoreDTO dto) {
		StorePO po = StoreConverter.toPO(dto);
		int rt = storeWriteManage.deleteStoreWithTx(po);		
		return rt;
	}
}
	