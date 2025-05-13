package com.egeo.components.order.manage.write;

import com.egeo.components.order.po.SoPackageItemPO;


public interface SoPackageItemWriteManage {

	int insertSoPackageItemWithTx(SoPackageItemPO po);

	int updateSoPackageItemWithTx(SoPackageItemPO po);

	int deleteSoPackageItemWithTx(SoPackageItemPO po);
}
	