package com.egeo.components.order.manage.write;

import com.egeo.components.order.po.SoDeliveryItemPO;


public interface SoDeliveryItemWriteManage {

	int insertSoDeliveryItemWithTx(SoDeliveryItemPO po);

	int updateSoDeliveryItemWithTx(SoDeliveryItemPO po);

	int deleteSoDeliveryItemWithTx(SoDeliveryItemPO po);
}
	