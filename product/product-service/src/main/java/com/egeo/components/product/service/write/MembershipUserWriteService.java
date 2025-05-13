package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.MembershipUserDTO;
import com.egeo.web.JsonResult;

import java.util.Date;
import java.util.List;


public interface MembershipUserWriteService {

	public Long insertMembershipUserWithTx(MembershipUserDTO dto);

	public int updateMembershipUserWithTx(MembershipUserDTO dto);

	public int deleteMembershipUserWithTx(MembershipUserDTO dto);

    Boolean updateMembershipUserWithTx(List<Long> membewrshipUserId);

    boolean updateMembershipUserWithTx(List<Long> membewrshipUserId, Date endTime);

    void giveUserMembershipByOrder(Long userId, Long platformId, Long skuId);
}
	