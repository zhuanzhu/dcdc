package com.egeo.components.promotion.manage.write;

import com.egeo.components.promotion.po.PointsRulePO;


public interface PointsRuleWriteManage {

	Long insertPointsRuleWithTx(PointsRulePO po);

	int updatePointsRuleWithTx(PointsRulePO po);

	int deletePointsRuleWithTx(PointsRulePO po);
}
	