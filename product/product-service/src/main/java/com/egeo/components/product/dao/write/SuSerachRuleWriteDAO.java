package com.egeo.components.product.dao.write;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.SuSerachRulePO;
import com.egeo.orm.BaseWriteDAO;

import java.util.List;

public interface SuSerachRuleWriteDAO extends BaseWriteDAO<SuSerachRulePO> {
	/**
	 * 根据suId更新数据
	 * @param suSerachRulePO
	 * @return
	 */
	int updateSuSerachRuleBySuIdWithTx(@Param("po")SuSerachRulePO suSerachRulePO);

    void saveSuSerachRulet(@Param("poList")List<SuSerachRulePO> suSerachRuleList);

    void addSuSerachRuleList(@Param("poList")List<SuSerachRulePO> addList);

	void updateSuSerachRuleList(@Param("poList")List<SuSerachRulePO> updateList);
}
	