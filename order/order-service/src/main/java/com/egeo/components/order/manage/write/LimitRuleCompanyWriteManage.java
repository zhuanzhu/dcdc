package com.egeo.components.order.manage.write;

import com.egeo.components.order.po.LimitRuleCompanyPO;


public interface LimitRuleCompanyWriteManage {

	Long insertLimitRuleCompanyWithTx(LimitRuleCompanyPO po);

	int updateLimitRuleCompanyWithTx(LimitRuleCompanyPO po);

	int deleteLimitRuleCompanyWithTx(LimitRuleCompanyPO po);
}
	