package com.egeo.components.order.manage.read;

import java.util.List;
	
import com.egeo.components.order.po.LimitRuleCompanyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface LimitRuleCompanyReadManage {

	public LimitRuleCompanyPO findLimitRuleCompanyById(LimitRuleCompanyPO po);

	public PageResult<LimitRuleCompanyPO> findLimitRuleCompanyOfPage(LimitRuleCompanyPO po,Pagination page);

	public List<LimitRuleCompanyPO> findLimitRuleCompanyAll(LimitRuleCompanyPO po);
	/**
	 * 根据限购规则id查询限购规则与公司关系
	 * @param limitRuleId
	 * @return
	 */
	public List<Long> findCompanyByLimitRuleId(Long limitRuleId);

    Integer findLimitRuleCompanyCount(LimitRuleCompanyPO limitRuleCompanyPO);
}
	