package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.MembershipWriteService;
import com.egeo.components.product.manage.write.MembershipWriteManage;
import com.egeo.components.product.converter.MembershipConverter;
import com.egeo.components.product.dto.MembershipDTO;
import com.egeo.components.product.po.MembershipPO;

@Service("membershipWriteService")
public class MembershipWriteServiceImpl  implements MembershipWriteService {
	@Autowired
	private MembershipWriteManage membershipWriteManage;

	@Override
	public Long insertMembershipWithTx(MembershipDTO dto) {
		MembershipPO po = MembershipConverter.toPO(dto);
		Long rt = membershipWriteManage.insertMembershipWithTx(po);		
		return rt;
	}

	@Override
	public int updateMembershipWithTx(MembershipDTO dto) {
		MembershipPO po = MembershipConverter.toPO(dto);
		int rt = membershipWriteManage.updateMembershipWithTx(po);		
		return rt;
	}

	@Override
	public int deleteMembershipWithTx(MembershipDTO dto) {
		MembershipPO po = MembershipConverter.toPO(dto);
		int rt = membershipWriteManage.deleteMembershipWithTx(po);		
		return rt;
	}
}
	