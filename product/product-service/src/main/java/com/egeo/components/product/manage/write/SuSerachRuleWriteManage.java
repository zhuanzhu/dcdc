package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.SuSerachRulePO;

import java.util.List;


public interface SuSerachRuleWriteManage {

	Long insertSuSerachRuleWithTx(SuSerachRulePO po);

	int updateSuSerachRuleWithTx(SuSerachRulePO po);

	int deleteSuSerachRuleWithTx(SuSerachRulePO po);
	/**
	 * 根据suId更新数据
	 * @param suSerachRulePO
	 * @return
	 */
	int updateSuSerachRuleBySuIdWithTx(SuSerachRulePO suSerachRulePO);

    void saveSuSerachRulet(List<SuSerachRulePO> suSerachRuleList);

    void addSuSerachRuleList(List<SuSerachRulePO> addList);

	void updateSuSerachRuleList(List<SuSerachRulePO> updateList);
}
	