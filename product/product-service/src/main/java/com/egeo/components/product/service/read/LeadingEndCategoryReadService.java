package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.LeadingEndCategoryDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface LeadingEndCategoryReadService {

	public LeadingEndCategoryDTO findLeadingEndCategoryById(LeadingEndCategoryDTO dto);

	public PageResult<LeadingEndCategoryDTO> findLeadingEndCategoryOfPage(LeadingEndCategoryDTO dto,Pagination page);

	public List<LeadingEndCategoryDTO> findLeadingEndCategoryAll(LeadingEndCategoryDTO dto);
}
	