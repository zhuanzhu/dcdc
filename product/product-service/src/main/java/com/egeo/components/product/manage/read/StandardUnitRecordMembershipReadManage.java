package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.StandardUnitRecordMembershipPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardUnitRecordMembershipReadManage {

	public StandardUnitRecordMembershipPO findStandardUnitRecordMembershipById(StandardUnitRecordMembershipPO po);

	public PageResult<StandardUnitRecordMembershipPO> findStandardUnitRecordMembershipOfPage(StandardUnitRecordMembershipPO po,Pagination page);

	public List<StandardUnitRecordMembershipPO> findStandardUnitRecordMembershipAll(StandardUnitRecordMembershipPO po);
}
	