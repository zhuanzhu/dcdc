package com.egeo.components.pay.manage.write;

import com.egeo.components.pay.po.JdOrderAwaitQueuePO;


public interface JdOrderAwaitQueueWriteManage {

	Long insertJdOrderAwaitQueueWithTx(JdOrderAwaitQueuePO po);

	int updateJdOrderAwaitQueueWithTx(JdOrderAwaitQueuePO po);

	int deleteJdOrderAwaitQueueWithTx(JdOrderAwaitQueuePO po);
}
	