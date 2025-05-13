package com.egeo.components.finance.manage.write;

import com.egeo.components.finance.po.CompanyAccountPO;


public interface CompanyAccountWriteManage {

	Long insertCompanyAccountWithTx(CompanyAccountPO po);

	int updateCompanyAccountWithTx(CompanyAccountPO po);

	int deleteCompanyAccountWithTx(CompanyAccountPO po);

	/**
	 * 更改公司账户有效性
	 * @param id
	 * @param disabled
	 * @return
	 */
	int updateAccountDisable(Long id, Integer disabled);
}
	