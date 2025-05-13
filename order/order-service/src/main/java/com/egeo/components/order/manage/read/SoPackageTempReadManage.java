package com.egeo.components.order.manage.read;

import java.util.List;
	
import com.egeo.components.order.po.SoPackageTempPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SoPackageTempReadManage {

	public SoPackageTempPO findSoPackageTempById(SoPackageTempPO po);

	public PageResult<SoPackageTempPO> findSoPackageTempOfPage(SoPackageTempPO po,Pagination page);

	public List<SoPackageTempPO> findSoPackageTempAll(SoPackageTempPO po);
}
	