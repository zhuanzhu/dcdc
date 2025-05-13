package com.egeo.components.product.service.read;


import java.util.List;

import com.egeo.components.product.dto.MembershipAuthorityDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface MembershipAuthorityReadService {
	
	
	public MembershipAuthorityDTO findMembershipAuthorityById(MembershipAuthorityDTO dto);

	public PageResult<MembershipAuthorityDTO> findMembershipAuthorityOfPage(MembershipAuthorityDTO dto,Pagination page);

	public List<MembershipAuthorityDTO> findMembershipAuthorityAll(MembershipAuthorityDTO dto);
	
	List<MembershipAuthorityDTO> findModifyYesterday();

}
	