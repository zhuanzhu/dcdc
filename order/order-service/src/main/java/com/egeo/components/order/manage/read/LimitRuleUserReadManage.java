package com.egeo.components.order.manage.read;

import java.util.List;
	
import com.egeo.components.order.po.LimitRuleUserPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface LimitRuleUserReadManage {

	public LimitRuleUserPO findLimitRuleUserById(LimitRuleUserPO po);

	public PageResult<LimitRuleUserPO> findLimitRuleUserOfPage(LimitRuleUserPO po,Pagination page);

	public List<LimitRuleUserPO> findLimitRuleUserAll(LimitRuleUserPO po);

    Integer findLimitRuleAllByParam(LimitRuleUserPO limitRuleUserPO);
}
	