package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.LeadingEndCategoryPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface LeadingEndCategoryReadManage {

	public LeadingEndCategoryPO findLeadingEndCategoryById(LeadingEndCategoryPO po);

	public PageResult<LeadingEndCategoryPO> findLeadingEndCategoryOfPage(LeadingEndCategoryPO po,Pagination page);

	public List<LeadingEndCategoryPO> findLeadingEndCategoryAll(LeadingEndCategoryPO po);
}
	