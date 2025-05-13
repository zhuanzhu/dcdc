package com.egeo.components.pay.manage.read;

import java.util.List;

import com.egeo.components.pay.po.JdOrderAwaitQueuePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface JdOrderAwaitQueueReadManage {

	public JdOrderAwaitQueuePO findJdOrderAwaitQueueById(JdOrderAwaitQueuePO po);

	public PageResult<JdOrderAwaitQueuePO> findJdOrderAwaitQueueOfPage(JdOrderAwaitQueuePO po,Pagination page);

	public List<JdOrderAwaitQueuePO> findJdOrderAwaitQueueAll(JdOrderAwaitQueuePO po);
}
	