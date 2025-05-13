package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.InsuranceLoginPO;


public interface InsuranceLoginWriteManage {

	Long insertInsuranceLoginWithTx(InsuranceLoginPO po);

	int updateInsuranceLoginWithTx(InsuranceLoginPO po);

	int deleteInsuranceLoginWithTx(InsuranceLoginPO po);

	/**
	 * 保险登陆更新用户信息
	 * @param po
	 * @return
	 */
	int insuranceLoginWithTx(InsuranceLoginPO po);
}
	