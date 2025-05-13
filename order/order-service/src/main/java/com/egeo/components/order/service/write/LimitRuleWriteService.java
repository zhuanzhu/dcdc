package com.egeo.components.order.service.write;

import java.util.List;

import com.egeo.components.order.dto.LimitRuleDTO;


public interface LimitRuleWriteService {
	// 废除
	public Long insertLimitRuleWithTx(LimitRuleDTO dto, List<Long> companyIdList, List<Long> userCompanyIdList, List<Long> storeIdList);

	public int updateLimitRuleWithTx(LimitRuleDTO dto, List<Long> companyIdList, List<Long> userCompanyIdList, List<Long> storeIdList);

	public int deleteLimitRuleWithTx(LimitRuleDTO dto);
	/**
	 * 根据限购规则id启用停用限购规则
	 * @param limitRuleId
	 * @param isStart
	 * @return
	 */
	public int isLimitRuleStartWithTx(Long limitRuleId, Integer isStart);
}
	