package com.egeo.components.order.manage.read;

import java.util.List;
	
import com.egeo.components.order.po.FrozenPuPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface FrozenPuReadManage {

	public FrozenPuPO findFrozenPuById(FrozenPuPO po);

	public PageResult<FrozenPuPO> findFrozenPuOfPage(FrozenPuPO po,Pagination page);

	public List<FrozenPuPO> findFrozenPuAll(FrozenPuPO po);
}
	