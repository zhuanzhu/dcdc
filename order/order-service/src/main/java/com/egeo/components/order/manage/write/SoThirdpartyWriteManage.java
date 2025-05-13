package com.egeo.components.order.manage.write;

import com.egeo.components.order.po.SoThirdpartyPO;


public interface SoThirdpartyWriteManage {

	Long insertSoThirdpartyWithTx(SoThirdpartyPO po);

	int updateSoThirdpartyWithTx(SoThirdpartyPO po);

	int deleteSoThirdpartyWithTx(SoThirdpartyPO po);

    int updateSoThirdpartyAndSoWithTx(SoThirdpartyPO po);

    void updateSoThirdpartyByCodeWithTx(SoThirdpartyPO po);

    void updateSoThirdpartyByThirdIdWithTx(SoThirdpartyPO po);
}
	