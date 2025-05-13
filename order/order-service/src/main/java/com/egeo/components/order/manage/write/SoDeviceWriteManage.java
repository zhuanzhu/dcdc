package com.egeo.components.order.manage.write;

import com.egeo.components.order.po.SoDevicePO;


public interface SoDeviceWriteManage {

	Long insertSoDeviceWithTx(SoDevicePO po);

	int updateSoDeviceWithTx(SoDevicePO po);

	int deleteSoDeviceWithTx(SoDevicePO po);
}
	