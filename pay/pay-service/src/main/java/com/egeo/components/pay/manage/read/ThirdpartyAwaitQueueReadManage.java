package com.egeo.components.pay.manage.read;

import java.util.List;

import com.egeo.components.pay.po.ThirdpartyAwaitQueuePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ThirdpartyAwaitQueueReadManage {

	public ThirdpartyAwaitQueuePO findThirdpartyAwaitQueueById(ThirdpartyAwaitQueuePO po);

	public PageResult<ThirdpartyAwaitQueuePO> findThirdpartyAwaitQueueOfPage(ThirdpartyAwaitQueuePO po,Pagination page);

	public List<ThirdpartyAwaitQueuePO> findThirdpartyAwaitQueueAll(ThirdpartyAwaitQueuePO po);
}
	