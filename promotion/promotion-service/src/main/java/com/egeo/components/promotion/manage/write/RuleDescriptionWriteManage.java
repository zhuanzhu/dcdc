package com.egeo.components.promotion.manage.write;

import com.egeo.components.promotion.po.RuleDescriptionPO;


public interface RuleDescriptionWriteManage {

	Long insertRuleDescriptionWithTx(RuleDescriptionPO po);

	int updateRuleDescriptionWithTx(RuleDescriptionPO po);

	int deleteRuleDescriptionWithTx(RuleDescriptionPO po);
}
	