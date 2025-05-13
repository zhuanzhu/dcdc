package com.egeo.components.product.manage.read;

import java.util.List;

import com.egeo.components.product.condition.MembershipAuthorityCondition;
import com.egeo.components.product.po.MembershipAuthorityPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface MembershipAuthorityReadManage {

	public MembershipAuthorityPO findMembershipAuthorityById(MembershipAuthorityPO po);

	public PageResult<MembershipAuthorityPO> findMembershipAuthorityOfPage(MembershipAuthorityPO po,Pagination page);

	public List<MembershipAuthorityPO> findMembershipAuthorityAll(MembershipAuthorityPO po);
	
	List<MembershipAuthorityCondition> findModifyYesterday();
}
	