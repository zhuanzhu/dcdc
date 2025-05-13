package com.egeo.components.promotion.business;

import java.util.List;
	
import com.egeo.components.promotion.dto.RuleDescriptionDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface RuleDescriptionManage {

	public RuleDescriptionDTO findRuleDescriptionById(RuleDescriptionDTO dto);	

	public PageResult<RuleDescriptionDTO> findRuleDescriptionOfPage(RuleDescriptionDTO dto,Pagination page);

	public List<RuleDescriptionDTO> findRuleDescriptionAll(RuleDescriptionDTO dto);

	Long insertRuleDescriptionWithTx(RuleDescriptionDTO dto);

	int updateRuleDescriptionWithTx(RuleDescriptionDTO dto);

	int deleteRuleDescriptionWithTx(RuleDescriptionDTO dto);
}
	