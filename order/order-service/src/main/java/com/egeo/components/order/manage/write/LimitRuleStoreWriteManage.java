package com.egeo.components.order.manage.write;

import com.egeo.components.order.po.LimitRuleStorePO;


public interface LimitRuleStoreWriteManage {

	Long insertLimitRuleStoreWithTx(LimitRuleStorePO po);

	int updateLimitRuleStoreWithTx(LimitRuleStorePO po);

	int deleteLimitRuleStoreWithTx(LimitRuleStorePO po);
}
	