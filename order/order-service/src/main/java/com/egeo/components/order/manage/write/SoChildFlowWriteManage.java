package com.egeo.components.order.manage.write;

import com.egeo.components.order.po.SoChildFlowPO;


public interface SoChildFlowWriteManage {

	Long insertSoChildFlowWithTx(SoChildFlowPO po);

	int updateSoChildFlowWithTx(SoChildFlowPO po);

	int deleteSoChildFlowWithTx(SoChildFlowPO po);
}
	