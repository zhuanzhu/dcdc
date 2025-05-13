package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.write.StoreTreeNodeWriteService;
import com.egeo.components.product.manage.write.StoreTreeNodeWriteManage;
import com.egeo.components.product.converter.StoreConverter;
import com.egeo.components.product.converter.StoreTreeNodeConverter;
import com.egeo.components.product.dto.StoreDTO;
import com.egeo.components.product.dto.StoreTreeNodeDTO;
import com.egeo.components.product.po.StorePO;
import com.egeo.components.product.po.StoreTreeNodePO;


@Service("storeTreeNodeWriteService")
public class StoreTreeNodeWriteServiceImpl  implements StoreTreeNodeWriteService {
	@Autowired
	private StoreTreeNodeWriteManage storeTreeNodeWriteManage;

	@Override
	public Long insertStoreTreeNodeWithTx(StoreTreeNodeDTO storeTreeNodedto,StoreDTO storedto) {
		StoreTreeNodePO storeTreeNodePO = StoreTreeNodeConverter.toPO(storeTreeNodedto);
		StorePO storePO = StoreConverter.toPO(storedto);
		Long rt = storeTreeNodeWriteManage.insertStoreTreeNodeWithTx(storeTreeNodePO,storePO);		
		return rt;
	}

	@Override
	public int updateStoreTreeNodeWithTx(StoreTreeNodeDTO dto) {
		StoreTreeNodePO po = StoreTreeNodeConverter.toPO(dto);
		int rt = storeTreeNodeWriteManage.updateStoreTreeNodeWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStoreTreeNodeWithTx(StoreTreeNodeDTO dto) {
		StoreTreeNodePO po = StoreTreeNodeConverter.toPO(dto);
		int rt = storeTreeNodeWriteManage.deleteStoreTreeNodeWithTx(po);		
		return rt;
	}
}
	