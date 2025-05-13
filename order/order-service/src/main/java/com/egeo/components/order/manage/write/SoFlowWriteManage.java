package com.egeo.components.order.manage.write;

import com.egeo.components.order.po.SoFlowPO;


public interface SoFlowWriteManage {

	Long insertSoFlowWithTx(SoFlowPO po);

	int updateSoFlowWithTx(SoFlowPO po);

	int deleteSoFlowWithTx(SoFlowPO po);
}
	