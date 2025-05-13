package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.StandardUnitRecordWriteService;
import com.egeo.components.product.manage.write.StandardUnitRecordWriteManage;
import com.egeo.components.product.converter.StandardUnitRecordConverter;
import com.egeo.components.product.dto.StandardUnitRecordDTO;
import com.egeo.components.product.po.StandardUnitRecordPO;

@Service("standardUnitRecordWriteService")
public class StandardUnitRecordWriteServiceImpl  implements StandardUnitRecordWriteService {
	@Autowired
	private StandardUnitRecordWriteManage standardUnitRecordWriteManage;

	@Override
	public Long insertStandardUnitRecordWithTx(StandardUnitRecordDTO dto) {
		StandardUnitRecordPO po = StandardUnitRecordConverter.toPO(dto);
		Long rt = standardUnitRecordWriteManage.insertStandardUnitRecordWithTx(po);		
		return rt;
	}

	@Override
	public int updateStandardUnitRecordWithTx(StandardUnitRecordDTO dto) {
		StandardUnitRecordPO po = StandardUnitRecordConverter.toPO(dto);
		int rt = standardUnitRecordWriteManage.updateStandardUnitRecordWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStandardUnitRecordWithTx(StandardUnitRecordDTO dto) {
		StandardUnitRecordPO po = StandardUnitRecordConverter.toPO(dto);
		int rt = standardUnitRecordWriteManage.deleteStandardUnitRecordWithTx(po);		
		return rt;
	}
}
	