package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.CompanyPagePO;


public interface CompanyPageWriteManage {

	Long insertCompanyPageWithTx(CompanyPagePO po);

	int updateCompanyPageWithTx(CompanyPagePO po);

	int deleteCompanyPageWithTx(CompanyPagePO po);
}
	