package com.egeo.components.promotion.service.write;

import com.egeo.components.promotion.dto.RuleDescriptionDTO;


public interface RuleDescriptionWriteService {

	public Long insertRuleDescriptionWithTx(RuleDescriptionDTO dto);

	public int updateRuleDescriptionWithTx(RuleDescriptionDTO dto);

	public int deleteRuleDescriptionWithTx(RuleDescriptionDTO dto);
}
	