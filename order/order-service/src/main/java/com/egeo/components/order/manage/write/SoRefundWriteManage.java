package com.egeo.components.order.manage.write;

import com.egeo.components.order.po.SoRefundPO;


public interface SoRefundWriteManage {

	Long insertSoRefundWithTx(SoRefundPO po);

	int updateSoRefundWithTx(SoRefundPO po);

	int deleteSoRefundWithTx(SoRefundPO po);
}
	