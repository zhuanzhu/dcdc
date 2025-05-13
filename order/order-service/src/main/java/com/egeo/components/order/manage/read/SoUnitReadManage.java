package com.egeo.components.order.manage.read;

import java.util.List;
	
import com.egeo.components.order.po.SoUnitPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SoUnitReadManage {

	public SoUnitPO findSoUnitById(SoUnitPO po);

	public PageResult<SoUnitPO> findSoUnitOfPage(SoUnitPO po,Pagination page);

	public List<SoUnitPO> findSoUnitAll(SoUnitPO po);
}
	