package com.egeo.components.product.service.read;


import java.util.List;

import com.egeo.components.product.dto.MembershipDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface MembershipReadService {

	public MembershipDTO findMembershipById(MembershipDTO dto);

	public PageResult<MembershipDTO> findMembershipOfPage(MembershipDTO dto,Pagination page);

	public List<MembershipDTO> findMembershipAll(MembershipDTO dto);

	PageResult<MembershipDTO> findMembershipOfPageByParam(Long categoryId, String membershipName, List<Long> skuIdList , Long platformId, Pagination page);

    MembershipDTO findMembershipBySkuId(Long skuId, Long platformId);
    
    List<MembershipDTO> findNotifyMembership(Integer remainDays);
    
}
	