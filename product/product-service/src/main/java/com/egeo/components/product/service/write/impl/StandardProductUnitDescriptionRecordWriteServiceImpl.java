package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.StandardProductUnitDescriptionRecordWriteService;
import com.egeo.components.product.manage.write.StandardProductUnitDescriptionRecordWriteManage;
import com.egeo.components.product.converter.StandardProductUnitDescriptionRecordConverter;
import com.egeo.components.product.dto.StandardProductUnitDescriptionRecordDTO;
import com.egeo.components.product.po.StandardProductUnitDescriptionRecordPO;

@Service("standardProductUnitDescriptionRecordWriteService")
public class StandardProductUnitDescriptionRecordWriteServiceImpl  implements StandardProductUnitDescriptionRecordWriteService {
	@Autowired
	private StandardProductUnitDescriptionRecordWriteManage standardProductUnitDescriptionRecordWriteManage;

	@Override
	public Long insertStandardProductUnitDescriptionRecordWithTx(StandardProductUnitDescriptionRecordDTO dto) {
		StandardProductUnitDescriptionRecordPO po = StandardProductUnitDescriptionRecordConverter.toPO(dto);
		Long rt = standardProductUnitDescriptionRecordWriteManage.insertStandardProductUnitDescriptionRecordWithTx(po);		
		return rt;
	}

	@Override
	public int updateStandardProductUnitDescriptionRecordWithTx(StandardProductUnitDescriptionRecordDTO dto) {
		StandardProductUnitDescriptionRecordPO po = StandardProductUnitDescriptionRecordConverter.toPO(dto);
		int rt = standardProductUnitDescriptionRecordWriteManage.updateStandardProductUnitDescriptionRecordWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStandardProductUnitDescriptionRecordWithTx(StandardProductUnitDescriptionRecordDTO dto) {
		StandardProductUnitDescriptionRecordPO po = StandardProductUnitDescriptionRecordConverter.toPO(dto);
		int rt = standardProductUnitDescriptionRecordWriteManage.deleteStandardProductUnitDescriptionRecordWithTx(po);		
		return rt;
	}
}
	