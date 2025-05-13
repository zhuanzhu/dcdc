package com.egeo.components.order.manage.write;

import com.egeo.components.order.po.SoPackageBoxPO;


public interface SoPackageBoxWriteManage {

	Long insertSoPackageBoxWithTx(SoPackageBoxPO po);

	int updateSoPackageBoxWithTx(SoPackageBoxPO po);

	int deleteSoPackageBoxWithTx(SoPackageBoxPO po);
}
	