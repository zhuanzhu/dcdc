package com.egeo.components.promotion.service.read;


import java.util.List;
	
import com.egeo.components.promotion.dto.RuleDescriptionDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface RuleDescriptionReadService {

	public RuleDescriptionDTO findRuleDescriptionById(RuleDescriptionDTO dto);

	public PageResult<RuleDescriptionDTO> findRuleDescriptionOfPage(RuleDescriptionDTO dto,Pagination page);

	public List<RuleDescriptionDTO> findRuleDescriptionAll(RuleDescriptionDTO dto);
}
	