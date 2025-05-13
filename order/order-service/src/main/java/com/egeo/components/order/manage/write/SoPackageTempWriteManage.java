package com.egeo.components.order.manage.write;

import java.util.List;

import com.egeo.components.order.po.SoPackageTempPO;


public interface SoPackageTempWriteManage {

	Long insertSoPackageTempWithTx(SoPackageTempPO po);

	int updateSoPackageTempWithTx(SoPackageTempPO po);

	int deleteSoPackageTempWithTx(SoPackageTempPO po);

	String insertSoPackageTempListWithTx(List<SoPackageTempPO> po);

}
	