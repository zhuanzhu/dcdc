package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.StandardUnitRecordStoreWriteService;
import com.egeo.components.product.manage.write.StandardUnitRecordStoreWriteManage;
import com.egeo.components.product.converter.StandardUnitRecordStoreConverter;
import com.egeo.components.product.dto.StandardUnitRecordStoreDTO;
import com.egeo.components.product.po.StandardUnitRecordStorePO;

@Service("standardUnitRecordStoreWriteService")
public class StandardUnitRecordStoreWriteServiceImpl  implements StandardUnitRecordStoreWriteService {
	@Autowired
	private StandardUnitRecordStoreWriteManage standardUnitRecordStoreWriteManage;

	@Override
	public Long insertStandardUnitRecordStoreWithTx(StandardUnitRecordStoreDTO dto) {
		StandardUnitRecordStorePO po = StandardUnitRecordStoreConverter.toPO(dto);
		Long rt = standardUnitRecordStoreWriteManage.insertStandardUnitRecordStoreWithTx(po);		
		return rt;
	}

	@Override
	public int updateStandardUnitRecordStoreWithTx(StandardUnitRecordStoreDTO dto) {
		StandardUnitRecordStorePO po = StandardUnitRecordStoreConverter.toPO(dto);
		int rt = standardUnitRecordStoreWriteManage.updateStandardUnitRecordStoreWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStandardUnitRecordStoreWithTx(StandardUnitRecordStoreDTO dto) {
		StandardUnitRecordStorePO po = StandardUnitRecordStoreConverter.toPO(dto);
		int rt = standardUnitRecordStoreWriteManage.deleteStandardUnitRecordStoreWithTx(po);		
		return rt;
	}
}
	