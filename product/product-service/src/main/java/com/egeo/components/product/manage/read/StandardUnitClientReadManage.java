package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.StandardUnitClientPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardUnitClientReadManage {

	public StandardUnitClientPO findStandardUnitClientById(StandardUnitClientPO po);

	public PageResult<StandardUnitClientPO> findStandardUnitClientOfPage(StandardUnitClientPO po,Pagination page);

	public List<StandardUnitClientPO> findStandardUnitClientAll(StandardUnitClientPO po);
}
	