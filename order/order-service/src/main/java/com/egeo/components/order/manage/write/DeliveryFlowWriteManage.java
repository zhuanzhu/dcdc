package com.egeo.components.order.manage.write;

import com.egeo.components.order.po.DeliveryFlowPO;


public interface DeliveryFlowWriteManage {

	Long insertDeliveryFlowWithTx(DeliveryFlowPO po);

	int updateDeliveryFlowWithTx(DeliveryFlowPO po);

	int deleteDeliveryFlowWithTx(DeliveryFlowPO po);
}
	