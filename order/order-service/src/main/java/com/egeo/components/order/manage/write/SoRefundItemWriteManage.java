package com.egeo.components.order.manage.write;

import com.egeo.components.order.po.SoRefundItemPO;


public interface SoRefundItemWriteManage {

	Long insertSoRefundItemWithTx(SoRefundItemPO po);

	int updateSoRefundItemWithTx(SoRefundItemPO po);

	int deleteSoRefundItemWithTx(SoRefundItemPO po);
}
	