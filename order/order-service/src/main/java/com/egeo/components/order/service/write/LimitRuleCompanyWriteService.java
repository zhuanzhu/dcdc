package com.egeo.components.order.service.write;

import com.egeo.components.order.dto.LimitRuleCompanyDTO;


public interface LimitRuleCompanyWriteService {

	public Long insertLimitRuleCompanyWithTx(LimitRuleCompanyDTO dto);

	public int updateLimitRuleCompanyWithTx(LimitRuleCompanyDTO dto);

	public int deleteLimitRuleCompanyWithTx(LimitRuleCompanyDTO dto);
}
	