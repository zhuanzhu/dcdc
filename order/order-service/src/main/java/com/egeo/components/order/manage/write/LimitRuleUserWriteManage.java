package com.egeo.components.order.manage.write;

import com.egeo.components.order.po.LimitRuleUserPO;


public interface LimitRuleUserWriteManage {

	Long insertLimitRuleUserWithTx(LimitRuleUserPO po);

	int updateLimitRuleUserWithTx(LimitRuleUserPO po);

	int deleteLimitRuleUserWithTx(LimitRuleUserPO po);
}
	