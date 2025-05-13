package com.egeo.components.product.manage.read;

import java.util.List;

import com.egeo.components.product.condition.MembershipCondition;
import com.egeo.components.product.po.MembershipPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface MembershipReadManage {

	public MembershipPO findMembershipById(MembershipPO po);

	public PageResult<MembershipPO> findMembershipOfPage(MembershipPO po,Pagination page);

	public List<MembershipPO> findMembershipAll(MembershipPO po);

	PageResult<MembershipPO> findMembershipOfPageByParam(Long categoryId, String membershipName,List<Long> skuIdList , Long platformId, Pagination page);

	MembershipPO findMembershipBySkuId(Long skuId, Long platformId);
	
	List<MembershipCondition> findNotifyMembership(Integer remainDays);
}
	