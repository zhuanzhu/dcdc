package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.StandardUnitClientRecordWriteService;
import com.egeo.components.product.manage.write.StandardUnitClientRecordWriteManage;
import com.egeo.components.product.converter.StandardUnitClientRecordConverter;
import com.egeo.components.product.dto.StandardUnitClientRecordDTO;
import com.egeo.components.product.po.StandardUnitClientRecordPO;

@Service("standardUnitClientRecordWriteService")
public class StandardUnitClientRecordWriteServiceImpl  implements StandardUnitClientRecordWriteService {
	@Autowired
	private StandardUnitClientRecordWriteManage standardUnitClientRecordWriteManage;

	@Override
	public Long insertStandardUnitClientRecordWithTx(StandardUnitClientRecordDTO dto) {
		StandardUnitClientRecordPO po = StandardUnitClientRecordConverter.toPO(dto);
		Long rt = standardUnitClientRecordWriteManage.insertStandardUnitClientRecordWithTx(po);		
		return rt;
	}

	@Override
	public int updateStandardUnitClientRecordWithTx(StandardUnitClientRecordDTO dto) {
		StandardUnitClientRecordPO po = StandardUnitClientRecordConverter.toPO(dto);
		int rt = standardUnitClientRecordWriteManage.updateStandardUnitClientRecordWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStandardUnitClientRecordWithTx(StandardUnitClientRecordDTO dto) {
		StandardUnitClientRecordPO po = StandardUnitClientRecordConverter.toPO(dto);
		int rt = standardUnitClientRecordWriteManage.deleteStandardUnitClientRecordWithTx(po);		
		return rt;
	}
}
	