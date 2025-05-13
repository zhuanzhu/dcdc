package com.egeo.components.order.service.write;

import com.egeo.components.order.dto.LimitRuleUserDTO;


public interface LimitRuleUserWriteService {

	public Long insertLimitRuleUserWithTx(LimitRuleUserDTO dto);

	public int updateLimitRuleUserWithTx(LimitRuleUserDTO dto);

	public int deleteLimitRuleUserWithTx(LimitRuleUserDTO dto);
}
	