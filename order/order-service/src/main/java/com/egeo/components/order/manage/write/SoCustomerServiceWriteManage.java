package com.egeo.components.order.manage.write;

import com.egeo.components.order.po.SoCustomerServicePO;


public interface SoCustomerServiceWriteManage {

	Long insertSoCustomerServiceWithTx(SoCustomerServicePO po);

	int updateSoCustomerServiceWithTx(SoCustomerServicePO po);

	int deleteSoCustomerServiceWithTx(SoCustomerServicePO po);
}
	