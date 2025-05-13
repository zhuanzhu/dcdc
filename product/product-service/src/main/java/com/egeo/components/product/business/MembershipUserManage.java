package com.egeo.components.product.business;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.egeo.components.product.vo.MembershipUserVO;
import com.egeo.components.product.dto.MembershipUserDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;

public interface MembershipUserManage {

	/*public MembershipUserDTO findMembershipUserById(MembershipUserDTO dto);

	public PageResult<MembershipUserDTO> findMembershipUserOfPage(MembershipUserDTO dto,Pagination page);

	public List<MembershipUserDTO> findMembershipUserAll(MembershipUserDTO dto);

	Long insertMembershipUserWithTx(MembershipUserDTO dto);

	int updateMembershipUserWithTx(MembershipUserDTO dto);

	int deleteMembershipUserWithTx(MembershipUserDTO dto);*/

    JsonResult<PageResult<MembershipUserVO>> findMembershipUserAll(Long membershipId, String name, String mail, String mobile, Integer sex, Date birthday, String companyName, Date startTime, Date endTime, Integer statusCode, Long platformId,Pagination page);

    JsonResult<Object> stopUserMembership(List<Long> membewrshipUserId);

    JsonResult<Object> extendUserMembershipDate(List<Long> membewrshipUserId, Date endTime);

    JsonResult<Map<String,Object>> membershipUserImportWithTx(Long platformId,Long membershipId, List<Map<String,Object>> valueList,Integer tempType);

    JsonResult<String> assureMembershipUserImport( String fileNumber);

    JsonResult<Map<String,Object>> findMembershipByUser(Long userId, Long platformId);
}
	