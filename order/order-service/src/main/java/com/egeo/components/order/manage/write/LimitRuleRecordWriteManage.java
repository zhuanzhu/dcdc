package com.egeo.components.order.manage.write;

import com.egeo.components.order.po.LimitRuleRecordPO;


public interface LimitRuleRecordWriteManage {

	Long insertLimitRuleRecordWithTx(LimitRuleRecordPO po);

	int updateLimitRuleRecordWithTx(LimitRuleRecordPO po);

	int deleteLimitRuleRecordWithTx(LimitRuleRecordPO po);

	void updateOrderStatus(String orderCode, Integer orderStatus);
}
	