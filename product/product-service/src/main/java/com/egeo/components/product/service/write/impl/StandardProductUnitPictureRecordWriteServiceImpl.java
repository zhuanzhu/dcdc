package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.StandardProductUnitPictureRecordWriteService;
import com.egeo.components.product.manage.write.StandardProductUnitPictureRecordWriteManage;
import com.egeo.components.product.converter.StandardProductUnitPictureRecordConverter;
import com.egeo.components.product.dto.StandardProductUnitPictureRecordDTO;
import com.egeo.components.product.po.StandardProductUnitPictureRecordPO;

@Service("standardProductUnitPictureRecordWriteService")
public class StandardProductUnitPictureRecordWriteServiceImpl  implements StandardProductUnitPictureRecordWriteService {
	@Autowired
	private StandardProductUnitPictureRecordWriteManage standardProductUnitPictureRecordWriteManage;

	@Override
	public Long insertStandardProductUnitPictureRecordWithTx(StandardProductUnitPictureRecordDTO dto) {
		StandardProductUnitPictureRecordPO po = StandardProductUnitPictureRecordConverter.toPO(dto);
		Long rt = standardProductUnitPictureRecordWriteManage.insertStandardProductUnitPictureRecordWithTx(po);		
		return rt;
	}

	@Override
	public int updateStandardProductUnitPictureRecordWithTx(StandardProductUnitPictureRecordDTO dto) {
		StandardProductUnitPictureRecordPO po = StandardProductUnitPictureRecordConverter.toPO(dto);
		int rt = standardProductUnitPictureRecordWriteManage.updateStandardProductUnitPictureRecordWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStandardProductUnitPictureRecordWithTx(StandardProductUnitPictureRecordDTO dto) {
		StandardProductUnitPictureRecordPO po = StandardProductUnitPictureRecordConverter.toPO(dto);
		int rt = standardProductUnitPictureRecordWriteManage.deleteStandardProductUnitPictureRecordWithTx(po);		
		return rt;
	}
}
	