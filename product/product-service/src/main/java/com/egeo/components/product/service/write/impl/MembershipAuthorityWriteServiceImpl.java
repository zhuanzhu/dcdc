package com.egeo.components.product.service.write.impl;

import com.egeo.components.product.converter.MembershipConverter;
import com.egeo.components.product.dto.MembershipDTO;
import com.egeo.components.product.po.MembershipPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.MembershipAuthorityWriteService;
import com.egeo.components.product.manage.write.MembershipAuthorityWriteManage;
import com.egeo.components.product.converter.MembershipAuthorityConverter;
import com.egeo.components.product.dto.MembershipAuthorityDTO;
import com.egeo.components.product.po.MembershipAuthorityPO;

import java.util.List;

@Service("membershipAuthorityWriteService")
public class MembershipAuthorityWriteServiceImpl  implements MembershipAuthorityWriteService {
	@Autowired
	private MembershipAuthorityWriteManage membershipAuthorityWriteManage;

	@Override
	public Long insertMembershipAuthorityWithTx(MembershipAuthorityDTO dto) {
		MembershipAuthorityPO po = MembershipAuthorityConverter.toPO(dto);
		Long rt = membershipAuthorityWriteManage.insertMembershipAuthorityWithTx(po);		
		return rt;
	}

	@Override
	public int updateMembershipAuthorityWithTx(MembershipAuthorityDTO dto) {
		MembershipAuthorityPO po = MembershipAuthorityConverter.toPO(dto);
		int rt = membershipAuthorityWriteManage.updateMembershipAuthorityWithTx(po);		
		return rt;
	}

	@Override
	public int deleteMembershipAuthorityWithTx(MembershipAuthorityDTO dto) {
		MembershipAuthorityPO po = MembershipAuthorityConverter.toPO(dto);
		int rt = membershipAuthorityWriteManage.deleteMembershipAuthorityWithTx(po);		
		return rt;
	}

	@Override
	public void updateMembershipAuthorityWithTx(List<MembershipAuthorityDTO> dtos, MembershipDTO membershipDTO) {
		List<MembershipAuthorityPO> membershipAuthorityPOS = MembershipAuthorityConverter.toPO(dtos);
		MembershipPO membershipPO = MembershipConverter.toPO(membershipDTO);
		membershipAuthorityWriteManage.updateMembershipAuthorityWithTx(membershipAuthorityPOS,membershipPO);

	}

	@Override
	public void insertMembershipAuthorityWithTx(List<MembershipAuthorityDTO> dtos, MembershipDTO membershipDTO) {
		List<MembershipAuthorityPO> membershipAuthorityPOS = MembershipAuthorityConverter.toPO(dtos);
		MembershipPO membershipPO = MembershipConverter.toPO(membershipDTO);
		membershipAuthorityWriteManage.insertMembershipAuthorityWithTx(membershipAuthorityPOS,membershipPO);
		System.out.println("ok");
	}
}
	