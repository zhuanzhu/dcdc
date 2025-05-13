package com.egeo.components.user.manage.write;

import java.util.List;

import com.egeo.components.user.po.CompanyPO;
import com.egeo.components.user.po.CompanyPagePO;


public interface CompanyWriteManage {

	Long insertCompanyWithTx(CompanyPO po,List<CompanyPagePO> companyPageList);

	int updateCompanyWithTx(CompanyPO po,List<CompanyPagePO> companyPageList);

	int deleteCompanyWithTx(CompanyPO po);

    void updateCompanyParamWithTx(CompanyPO companyPO);
}
	