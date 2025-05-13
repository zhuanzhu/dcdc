package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.CompanyCallCenterPO;


public interface CompanyCallCenterWriteManage {

	Long insertCompanyCallCenterWithTx(CompanyCallCenterPO po);

	int updateCompanyCallCenterWithTx(CompanyCallCenterPO po);

	int deleteCompanyCallCenterWithTx(CompanyCallCenterPO po);
}
	