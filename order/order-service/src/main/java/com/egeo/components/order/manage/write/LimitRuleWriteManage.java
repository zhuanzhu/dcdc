package com.egeo.components.order.manage.write;

import java.util.List;

import com.egeo.components.order.po.LimitRulePO;


public interface LimitRuleWriteManage {

	Long insertLimitRuleWithTx(LimitRulePO po, List<Long> companyIdList, List<Long> userCompanyIdList, List<Long> storeIdList);

	int updateLimitRuleWithTx(LimitRulePO po, List<Long> companyIdList, List<Long> userCompanyIdList, List<Long> storeIdList);

	int deleteLimitRuleWithTx(LimitRulePO po);
	/**
	 * 根据限购规则id启用停用限购规则
	 * @param limitRuleId
	 * @param isStart
	 * @return
	 */
	int isLimitRuleStartWithTx(Long limitRuleId, Integer isStart);
}
	