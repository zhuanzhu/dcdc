package com.egeo.components.product.business;

import java.util.List;

import com.egeo.components.product.vo.MembershipAuthorityVO;
import com.egeo.components.product.dto.MembershipAuthorityDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;

public interface MembershipAuthorityManage {
	JsonResult<PageResult<MembershipAuthorityVO>> findMembershipAuthorityByMembershipId(Long membershipId, Long platformId,Pagination page);

    JsonResult<Object> updateMembershipAuthorityByMembershipId(Long membershipId, String membershipCode, String membershipName, String membershipLogImgUrl, Long linkedSkuId, Long categoryId, String categoryName, Integer validPeriodVal, Integer validPeriodUnit, List<Long> authorityIds, String remarks, Long platformId);


	/*public MembershipAuthorityDTO findMembershipAuthorityById(MembershipAuthorityDTO dto);

	public PageResult<MembershipAuthorityDTO> findMembershipAuthorityOfPage(MembershipAuthorityDTO dto,Pagination page);

	public List<MembershipAuthorityDTO> findMembershipAuthorityAll(MembershipAuthorityDTO dto);

	Long insertMembershipAuthorityWithTx(MembershipAuthorityDTO dto);

	int updateMembershipAuthorityWithTx(MembershipAuthorityDTO dto);

	int deleteMembershipAuthorityWithTx(MembershipAuthorityDTO dto);*/
}
	