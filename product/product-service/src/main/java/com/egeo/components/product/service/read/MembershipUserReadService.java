package com.egeo.components.product.service.read;


import java.util.Date;
import java.util.List;
	
import com.egeo.components.product.dto.MembershipUserDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface MembershipUserReadService {

	public MembershipUserDTO findMembershipUserById(MembershipUserDTO dto);

	public PageResult<MembershipUserDTO> findMembershipUserOfPage(MembershipUserDTO dto,Pagination page);

	public List<MembershipUserDTO> findMembershipUserAll(MembershipUserDTO dto);

    PageResult<MembershipUserDTO> findMembershipUserAllByParams(Long membershipId, Date startTime, Date endTime, Integer statusCode, Pagination page, List<Long> result);

    MembershipUserDTO findMembershipUserByMail(Long userId, Long membershipId);
}
	