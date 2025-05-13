package com.egeo.components.finance.manage.write;

import com.egeo.components.finance.po.SoFreezeFubiPO;


public interface SoFreezeFubiWriteManage {

	Long insertSoFreezeFubiWithTx(SoFreezeFubiPO po);

	int updateSoFreezeFubiWithTx(SoFreezeFubiPO po);

	int deleteSoFreezeFubiWithTx(SoFreezeFubiPO po);
	/**
	 * 根据订单id删除订单冻结积分
	 * @param soId
	 * @return
	 */
	int delBySoId(Long soId);
}
	