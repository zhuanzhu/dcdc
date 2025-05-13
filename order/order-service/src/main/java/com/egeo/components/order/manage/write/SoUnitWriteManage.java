package com.egeo.components.order.manage.write;

import com.egeo.components.order.po.SoUnitPO;


public interface SoUnitWriteManage {

	Long insertSoUnitWithTx(SoUnitPO po);

	int updateSoUnitWithTx(SoUnitPO po);

	int deleteSoUnitWithTx(SoUnitPO po);
}
	