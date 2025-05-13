package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.LeadingEndCategoryDTO;


public interface LeadingEndCategoryWriteService {

	public Long insertLeadingEndCategoryWithTx(LeadingEndCategoryDTO dto);

	public int updateLeadingEndCategoryWithTx(LeadingEndCategoryDTO dto);

	public int deleteLeadingEndCategoryWithTx(LeadingEndCategoryDTO dto);
}
	