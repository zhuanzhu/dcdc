package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.StandardUnitPictureRecordWriteService;
import com.egeo.components.product.manage.write.StandardUnitPictureRecordWriteManage;
import com.egeo.components.product.converter.StandardUnitPictureRecordConverter;
import com.egeo.components.product.dto.StandardUnitPictureRecordDTO;
import com.egeo.components.product.po.StandardUnitPictureRecordPO;

@Service("standardUnitPictureRecordWriteService")
public class StandardUnitPictureRecordWriteServiceImpl  implements StandardUnitPictureRecordWriteService {
	@Autowired
	private StandardUnitPictureRecordWriteManage standardUnitPictureRecordWriteManage;

	@Override
	public Long insertStandardUnitPictureRecordWithTx(StandardUnitPictureRecordDTO dto) {
		StandardUnitPictureRecordPO po = StandardUnitPictureRecordConverter.toPO(dto);
		Long rt = standardUnitPictureRecordWriteManage.insertStandardUnitPictureRecordWithTx(po);		
		return rt;
	}

	@Override
	public int updateStandardUnitPictureRecordWithTx(StandardUnitPictureRecordDTO dto) {
		StandardUnitPictureRecordPO po = StandardUnitPictureRecordConverter.toPO(dto);
		int rt = standardUnitPictureRecordWriteManage.updateStandardUnitPictureRecordWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStandardUnitPictureRecordWithTx(StandardUnitPictureRecordDTO dto) {
		StandardUnitPictureRecordPO po = StandardUnitPictureRecordConverter.toPO(dto);
		int rt = standardUnitPictureRecordWriteManage.deleteStandardUnitPictureRecordWithTx(po);		
		return rt;
	}
}
	