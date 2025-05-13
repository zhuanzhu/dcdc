package com.egeo.components.order.manage.read;

import java.util.List;
	
import com.egeo.components.order.po.LimitRuleStorePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface LimitRuleStoreReadManage {

	public LimitRuleStorePO findLimitRuleStoreById(LimitRuleStorePO po);

	public PageResult<LimitRuleStorePO> findLimitRuleStoreOfPage(LimitRuleStorePO po,Pagination page);

	public List<LimitRuleStorePO> findLimitRuleStoreAll(LimitRuleStorePO po);

    Integer findLimitRuleStoreCount(LimitRuleStorePO limitRuleStorePO);
}
	