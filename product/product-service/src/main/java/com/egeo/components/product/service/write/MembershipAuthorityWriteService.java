package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.MembershipAuthorityDTO;
import com.egeo.components.product.dto.MembershipDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;


public interface MembershipAuthorityWriteService {

	public Long insertMembershipAuthorityWithTx(MembershipAuthorityDTO dto);

	public int updateMembershipAuthorityWithTx(MembershipAuthorityDTO dto);

	public int deleteMembershipAuthorityWithTx(MembershipAuthorityDTO dto);

    void updateMembershipAuthorityWithTx(List<MembershipAuthorityDTO> dtos, MembershipDTO membershipDTO);

    void insertMembershipAuthorityWithTx(List<MembershipAuthorityDTO> dtos, MembershipDTO membershipDTO);
}
	