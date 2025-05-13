package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.StandardUnitTagRecordWriteService;
import com.egeo.components.product.manage.write.StandardUnitTagRecordWriteManage;
import com.egeo.components.product.converter.StandardUnitTagRecordConverter;
import com.egeo.components.product.dto.StandardUnitTagRecordDTO;
import com.egeo.components.product.po.StandardUnitTagRecordPO;

@Service("standardUnitTagRecordWriteService")
public class StandardUnitTagRecordWriteServiceImpl  implements StandardUnitTagRecordWriteService {
	@Autowired
	private StandardUnitTagRecordWriteManage standardUnitTagRecordWriteManage;

	@Override
	public Long insertStandardUnitTagRecordWithTx(StandardUnitTagRecordDTO dto) {
		StandardUnitTagRecordPO po = StandardUnitTagRecordConverter.toPO(dto);
		Long rt = standardUnitTagRecordWriteManage.insertStandardUnitTagRecordWithTx(po);		
		return rt;
	}

	@Override
	public int updateStandardUnitTagRecordWithTx(StandardUnitTagRecordDTO dto) {
		StandardUnitTagRecordPO po = StandardUnitTagRecordConverter.toPO(dto);
		int rt = standardUnitTagRecordWriteManage.updateStandardUnitTagRecordWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStandardUnitTagRecordWithTx(StandardUnitTagRecordDTO dto) {
		StandardUnitTagRecordPO po = StandardUnitTagRecordConverter.toPO(dto);
		int rt = standardUnitTagRecordWriteManage.deleteStandardUnitTagRecordWithTx(po);		
		return rt;
	}
}
	