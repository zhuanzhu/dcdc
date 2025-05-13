package com.egeo.components.product.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.product.vo.MembershipResultVO;
import com.egeo.components.product.vo.MembershipVO;
import com.egeo.components.product.dto.MembershipDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;

public interface MembershipManage {

	/*


	public List<MembershipDTO> findMembershipAll(MembershipDTO dto);

	Long insertMembershipWithTx(MembershipDTO dto);

	int updateMembershipWithTx(MembershipDTO dto);

	int deleteMembershipWithTx(MembershipDTO dto);*/

	JsonResult<PageResult<MembershipVO>> findMembershipOfPage(Long categoryId, String membershipName, String linkedSkuName, Long platformId, Pagination page);
	public JsonResult<MembershipVO> findMembershipById(Long membershipId,Long platformId);

	List<MembershipResultVO> findMembershipAll(Long platformId);
}
	