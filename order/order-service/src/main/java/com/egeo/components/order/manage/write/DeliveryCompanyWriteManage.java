package com.egeo.components.order.manage.write;

import com.egeo.components.order.po.DeliveryCompanyPO;


public interface DeliveryCompanyWriteManage {

	int insertDeliveryCompanyWithTx(DeliveryCompanyPO po);

	int updateDeliveryCompanyWithTx(DeliveryCompanyPO po);

	int deleteDeliveryCompanyWithTx(DeliveryCompanyPO po);
}
	