package com.egeo.components.order.manage.write;

import com.egeo.components.order.po.SoDeliveryPO;


public interface SoDeliveryWriteManage {

	int insertSoDeliveryWithTx(SoDeliveryPO po);

	int updateSoDeliveryWithTx(SoDeliveryPO po);

	int deleteSoDeliveryWithTx(SoDeliveryPO po);
}
	