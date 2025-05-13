package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.StandardProductUnitAttValueRecordWriteService;
import com.egeo.components.product.manage.write.StandardProductUnitAttValueRecordWriteManage;
import com.egeo.components.product.converter.StandardProductUnitAttValueRecordConverter;
import com.egeo.components.product.dto.StandardProductUnitAttValueRecordDTO;
import com.egeo.components.product.po.StandardProductUnitAttValueRecordPO;

@Service("standardProductUnitAttValueRecordWriteService")
public class StandardProductUnitAttValueRecordWriteServiceImpl  implements StandardProductUnitAttValueRecordWriteService {
	@Autowired
	private StandardProductUnitAttValueRecordWriteManage standardProductUnitAttValueRecordWriteManage;

	@Override
	public Long insertStandardProductUnitAttValueRecordWithTx(StandardProductUnitAttValueRecordDTO dto) {
		StandardProductUnitAttValueRecordPO po = StandardProductUnitAttValueRecordConverter.toPO(dto);
		Long rt = standardProductUnitAttValueRecordWriteManage.insertStandardProductUnitAttValueRecordWithTx(po);		
		return rt;
	}

	@Override
	public int updateStandardProductUnitAttValueRecordWithTx(StandardProductUnitAttValueRecordDTO dto) {
		StandardProductUnitAttValueRecordPO po = StandardProductUnitAttValueRecordConverter.toPO(dto);
		int rt = standardProductUnitAttValueRecordWriteManage.updateStandardProductUnitAttValueRecordWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStandardProductUnitAttValueRecordWithTx(StandardProductUnitAttValueRecordDTO dto) {
		StandardProductUnitAttValueRecordPO po = StandardProductUnitAttValueRecordConverter.toPO(dto);
		int rt = standardProductUnitAttValueRecordWriteManage.deleteStandardProductUnitAttValueRecordWithTx(po);		
		return rt;
	}
}
	