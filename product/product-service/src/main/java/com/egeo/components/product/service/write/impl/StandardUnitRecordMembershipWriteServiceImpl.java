package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.StandardUnitRecordMembershipWriteService;
import com.egeo.components.product.manage.write.StandardUnitRecordMembershipWriteManage;
import com.egeo.components.product.converter.StandardUnitRecordMembershipConverter;
import com.egeo.components.product.dto.StandardUnitRecordMembershipDTO;
import com.egeo.components.product.po.StandardUnitRecordMembershipPO;

@Service("standardUnitRecordMembershipWriteService")
public class StandardUnitRecordMembershipWriteServiceImpl  implements StandardUnitRecordMembershipWriteService {
	@Autowired
	private StandardUnitRecordMembershipWriteManage standardUnitRecordMembershipWriteManage;

	@Override
	public Long insertStandardUnitRecordMembershipWithTx(StandardUnitRecordMembershipDTO dto) {
		StandardUnitRecordMembershipPO po = StandardUnitRecordMembershipConverter.toPO(dto);
		Long rt = standardUnitRecordMembershipWriteManage.insertStandardUnitRecordMembershipWithTx(po);		
		return rt;
	}

	@Override
	public int updateStandardUnitRecordMembershipWithTx(StandardUnitRecordMembershipDTO dto) {
		StandardUnitRecordMembershipPO po = StandardUnitRecordMembershipConverter.toPO(dto);
		int rt = standardUnitRecordMembershipWriteManage.updateStandardUnitRecordMembershipWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStandardUnitRecordMembershipWithTx(StandardUnitRecordMembershipDTO dto) {
		StandardUnitRecordMembershipPO po = StandardUnitRecordMembershipConverter.toPO(dto);
		int rt = standardUnitRecordMembershipWriteManage.deleteStandardUnitRecordMembershipWithTx(po);		
		return rt;
	}
}
	