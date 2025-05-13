package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.CompanyUserDisabledPO;


public interface CompanyUserDisabledWriteManage {

	Long insertCompanyUserDisabledWithTx(CompanyUserDisabledPO po);

	int updateCompanyUserDisabledWithTx(CompanyUserDisabledPO po);

	int deleteCompanyUserDisabledWithTx(CompanyUserDisabledPO po);

	public int updateRevalidationByCompanyId(Integer revalidation,Long companyId);
}
	