package com.egeo.components.order.service.write;

import com.egeo.components.order.dto.LimitRuleRecordDTO;


public interface LimitRuleRecordWriteService {

	public Long insertLimitRuleRecordWithTx(LimitRuleRecordDTO dto);

	public int updateLimitRuleRecordWithTx(LimitRuleRecordDTO dto);

	public int deleteLimitRuleRecordWithTx(LimitRuleRecordDTO dto);

	void updateOrderStatus(String orderCode, Integer orderStatus);
	
}
	