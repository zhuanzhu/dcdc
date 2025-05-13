package com.egeo.components.promotion.service.write;

import com.egeo.components.promotion.dto.PointsRuleDTO;


public interface PointsRuleWriteService {

	public Long insertPointsRuleWithTx(PointsRuleDTO dto);

	public int updatePointsRuleWithTx(PointsRuleDTO dto);

	public int deletePointsRuleWithTx(PointsRuleDTO dto);
}
	