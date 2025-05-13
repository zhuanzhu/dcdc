package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.StandardProductUnitAttNameRecordWriteService;
import com.egeo.components.product.manage.write.StandardProductUnitAttNameRecordWriteManage;
import com.egeo.components.product.converter.StandardProductUnitAttNameRecordConverter;
import com.egeo.components.product.dto.StandardProductUnitAttNameRecordDTO;
import com.egeo.components.product.po.StandardProductUnitAttNameRecordPO;

@Service("standardProductUnitAttNameRecordWriteService")
public class StandardProductUnitAttNameRecordWriteServiceImpl  implements StandardProductUnitAttNameRecordWriteService {
	@Autowired
	private StandardProductUnitAttNameRecordWriteManage standardProductUnitAttNameRecordWriteManage;

	@Override
	public Long insertStandardProductUnitAttNameRecordWithTx(StandardProductUnitAttNameRecordDTO dto) {
		StandardProductUnitAttNameRecordPO po = StandardProductUnitAttNameRecordConverter.toPO(dto);
		Long rt = standardProductUnitAttNameRecordWriteManage.insertStandardProductUnitAttNameRecordWithTx(po);		
		return rt;
	}

	@Override
	public int updateStandardProductUnitAttNameRecordWithTx(StandardProductUnitAttNameRecordDTO dto) {
		StandardProductUnitAttNameRecordPO po = StandardProductUnitAttNameRecordConverter.toPO(dto);
		int rt = standardProductUnitAttNameRecordWriteManage.updateStandardProductUnitAttNameRecordWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStandardProductUnitAttNameRecordWithTx(StandardProductUnitAttNameRecordDTO dto) {
		StandardProductUnitAttNameRecordPO po = StandardProductUnitAttNameRecordConverter.toPO(dto);
		int rt = standardProductUnitAttNameRecordWriteManage.deleteStandardProductUnitAttNameRecordWithTx(po);		
		return rt;
	}
}
	