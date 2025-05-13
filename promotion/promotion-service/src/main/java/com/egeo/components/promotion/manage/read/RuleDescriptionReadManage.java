package com.egeo.components.promotion.manage.read;

import java.util.List;
	
import com.egeo.components.promotion.po.RuleDescriptionPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface RuleDescriptionReadManage {

	public RuleDescriptionPO findRuleDescriptionById(RuleDescriptionPO po);

	public PageResult<RuleDescriptionPO> findRuleDescriptionOfPage(RuleDescriptionPO po,Pagination page);

	public List<RuleDescriptionPO> findRuleDescriptionAll(RuleDescriptionPO po);
}
	