package com.egeo.components.finance.manage.read;

import java.util.List;

import com.egeo.components.finance.po.ReasonCompanyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ReasonCompanyReadManage {

	public ReasonCompanyPO findReasonCompanyById(ReasonCompanyPO po);

	public PageResult<ReasonCompanyPO> findReasonCompanyOfPage(ReasonCompanyPO po,Pagination page);

	public List<ReasonCompanyPO> findReasonCompanyAll(ReasonCompanyPO po);
}
	