package com.egeo.components.order.manage.write;

import com.egeo.components.order.po.SoItemPO;

public interface SoItemWriteManage {

	Long updateSoItemWithTx(SoItemPO po);

	int updateSoItemRefundWithTx(SoItemPO po);
}
