package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.StandardUnitMembershipWriteService;
import com.egeo.components.product.manage.write.StandardUnitMembershipWriteManage;
import com.egeo.components.product.converter.StandardUnitMembershipConverter;
import com.egeo.components.product.dto.StandardUnitMembershipDTO;
import com.egeo.components.product.po.StandardUnitMembershipPO;

@Service("standardUnitMembershipWriteService")
public class StandardUnitMembershipWriteServiceImpl  implements StandardUnitMembershipWriteService {
	@Autowired
	private StandardUnitMembershipWriteManage standardUnitMembershipWriteManage;

	@Override
	public Long insertStandardUnitMembershipWithTx(StandardUnitMembershipDTO dto) {
		StandardUnitMembershipPO po = StandardUnitMembershipConverter.toPO(dto);
		Long rt = standardUnitMembershipWriteManage.insertStandardUnitMembershipWithTx(po);		
		return rt;
	}

	@Override
	public int updateStandardUnitMembershipWithTx(StandardUnitMembershipDTO dto) {
		StandardUnitMembershipPO po = StandardUnitMembershipConverter.toPO(dto);
		int rt = standardUnitMembershipWriteManage.updateStandardUnitMembershipWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStandardUnitMembershipWithTx(StandardUnitMembershipDTO dto) {
		StandardUnitMembershipPO po = StandardUnitMembershipConverter.toPO(dto);
		int rt = standardUnitMembershipWriteManage.deleteStandardUnitMembershipWithTx(po);		
		return rt;
	}
}
	