package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.LeadingEndCategoryPO;


public interface LeadingEndCategoryWriteManage {

	Long insertLeadingEndCategoryWithTx(LeadingEndCategoryPO po);

	int updateLeadingEndCategoryWithTx(LeadingEndCategoryPO po);

	int deleteLeadingEndCategoryWithTx(LeadingEndCategoryPO po);
}
	