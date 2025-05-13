package com.egeo.components.promotion.manage.read;

import java.util.List;
	
import com.egeo.components.promotion.po.ECardTempPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ECardTempReadManage {

	public ECardTempPO findECardTempById(ECardTempPO po);

	public PageResult<ECardTempPO> findECardTempOfPage(ECardTempPO po,Pagination page);

	public List<ECardTempPO> findECardTempAll(ECardTempPO po);
}
	