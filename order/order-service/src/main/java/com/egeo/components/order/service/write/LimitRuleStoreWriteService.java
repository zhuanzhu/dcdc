package com.egeo.components.order.service.write;

import com.egeo.components.order.dto.LimitRuleStoreDTO;


public interface LimitRuleStoreWriteService {

	public Long insertLimitRuleStoreWithTx(LimitRuleStoreDTO dto);

	public int updateLimitRuleStoreWithTx(LimitRuleStoreDTO dto);

	public int deleteLimitRuleStoreWithTx(LimitRuleStoreDTO dto);
}
	