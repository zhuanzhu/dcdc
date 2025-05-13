package com.egeo.components.user.manage.read;

import java.util.List;
	
import com.egeo.components.user.po.CompanyUserDisabledPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CompanyUserDisabledReadManage {

	public CompanyUserDisabledPO findCompanyUserDisabledById(CompanyUserDisabledPO po);

	public PageResult<CompanyUserDisabledPO> findCompanyUserDisabledOfPage(CompanyUserDisabledPO po,Pagination page);

	public List<CompanyUserDisabledPO> findCompanyUserDisabledAll(CompanyUserDisabledPO po);

	public Integer findRevalidationByCompanyId(Long companyId);

	public List<Long> findUsersByCompanyId(Long companyId);
}
	