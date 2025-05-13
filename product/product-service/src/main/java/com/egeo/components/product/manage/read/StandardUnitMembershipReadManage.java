package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.StandardUnitMembershipPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardUnitMembershipReadManage {

	public StandardUnitMembershipPO findStandardUnitMembershipById(StandardUnitMembershipPO po);

	public PageResult<StandardUnitMembershipPO> findStandardUnitMembershipOfPage(StandardUnitMembershipPO po,Pagination page);

	public List<StandardUnitMembershipPO> findStandardUnitMembershipAll(StandardUnitMembershipPO po);
}
	