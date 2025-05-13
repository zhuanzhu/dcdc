package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.SuSerachRulePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SuSerachRuleReadManage {

	public SuSerachRulePO findSuSerachRuleById(SuSerachRulePO po);

	public PageResult<SuSerachRulePO> findSuSerachRuleOfPage(SuSerachRulePO po,Pagination page);

	public List<SuSerachRulePO> findSuSerachRuleAll(SuSerachRulePO po);

    Integer findSuSerachRuleListSize(Long id);
}
	