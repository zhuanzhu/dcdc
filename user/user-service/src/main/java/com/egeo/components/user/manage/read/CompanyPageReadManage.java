package com.egeo.components.user.manage.read;

import java.util.List;
	
import com.egeo.components.user.po.CompanyPagePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CompanyPageReadManage {

	public CompanyPagePO findCompanyPageById(CompanyPagePO po);

	public PageResult<CompanyPagePO> findCompanyPageOfPage(CompanyPagePO po,Pagination page);

	public List<CompanyPagePO> findCompanyPageAll(CompanyPagePO po);
}
	