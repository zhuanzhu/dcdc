package com.egeo.components.product.manage.read;

import java.util.Date;
import java.util.List;
	
import com.egeo.components.product.po.MembershipUserPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface MembershipUserReadManage {

	public MembershipUserPO findMembershipUserById(MembershipUserPO po);

	public PageResult<MembershipUserPO> findMembershipUserOfPage(MembershipUserPO po,Pagination page);

	public List<MembershipUserPO> findMembershipUserAll(MembershipUserPO po);

    PageResult<MembershipUserPO> findMembershipUserAllByParams(Long membershipId, Date startTime, Date endTime, Integer statusCode, Pagination page, List<Long> result);
}
	