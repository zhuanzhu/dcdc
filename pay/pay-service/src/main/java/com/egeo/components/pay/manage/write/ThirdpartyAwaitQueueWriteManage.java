package com.egeo.components.pay.manage.write;

import com.egeo.components.pay.po.ThirdpartyAwaitQueuePO;


public interface ThirdpartyAwaitQueueWriteManage {

	Long insertThirdpartyAwaitQueueWithTx(ThirdpartyAwaitQueuePO po);

	int updateThirdpartyAwaitQueueWithTx(ThirdpartyAwaitQueuePO po);

	int deleteThirdpartyAwaitQueueWithTx(ThirdpartyAwaitQueuePO po);

    int deleteThirdpartyAwaitQueueByCodeWithTx(ThirdpartyAwaitQueuePO po);
}
	