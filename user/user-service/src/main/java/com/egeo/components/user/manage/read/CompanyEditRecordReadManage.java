package com.egeo.components.user.manage.read;

import java.util.List;
	
import com.egeo.components.user.po.CompanyEditRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CompanyEditRecordReadManage {

	public CompanyEditRecordPO findCompanyEditRecordById(CompanyEditRecordPO po);

	public PageResult<CompanyEditRecordPO> findCompanyEditRecordOfPage(CompanyEditRecordPO po,Pagination page);

	public List<CompanyEditRecordPO> findCompanyEditRecordAll(CompanyEditRecordPO po);
}
	