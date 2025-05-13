package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.StandardProductUnitRecordWriteService;
import com.egeo.components.product.manage.write.StandardProductUnitRecordWriteManage;
import com.egeo.components.product.converter.StandardProductUnitRecordConverter;
import com.egeo.components.product.dto.StandardProductUnitRecordDTO;
import com.egeo.components.product.po.StandardProductUnitRecordPO;

@Service("standardProductUnitRecordWriteService")
public class StandardProductUnitRecordWriteServiceImpl  implements StandardProductUnitRecordWriteService {
	@Autowired
	private StandardProductUnitRecordWriteManage standardProductUnitRecordWriteManage;

	@Override
	public Long insertStandardProductUnitRecordWithTx(StandardProductUnitRecordDTO dto) {
		StandardProductUnitRecordPO po = StandardProductUnitRecordConverter.toPO(dto);
		Long rt = standardProductUnitRecordWriteManage.insertStandardProductUnitRecordWithTx(po);		
		return rt;
	}

	@Override
	public int updateStandardProductUnitRecordWithTx(StandardProductUnitRecordDTO dto) {
		StandardProductUnitRecordPO po = StandardProductUnitRecordConverter.toPO(dto);
		int rt = standardProductUnitRecordWriteManage.updateStandardProductUnitRecordWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStandardProductUnitRecordWithTx(StandardProductUnitRecordDTO dto) {
		StandardProductUnitRecordPO po = StandardProductUnitRecordConverter.toPO(dto);
		int rt = standardProductUnitRecordWriteManage.deleteStandardProductUnitRecordWithTx(po);		
		return rt;
	}
}
	