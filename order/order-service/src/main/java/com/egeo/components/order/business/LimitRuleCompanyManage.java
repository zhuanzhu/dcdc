package com.egeo.components.order.business;

import java.util.List;
	
import com.egeo.components.order.dto.LimitRuleCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface LimitRuleCompanyManage {

	public LimitRuleCompanyDTO findLimitRuleCompanyById(LimitRuleCompanyDTO dto);	

	public PageResult<LimitRuleCompanyDTO> findLimitRuleCompanyOfPage(LimitRuleCompanyDTO dto,Pagination page);

	public List<LimitRuleCompanyDTO> findLimitRuleCompanyAll(LimitRuleCompanyDTO dto);

	Long insertLimitRuleCompanyWithTx(LimitRuleCompanyDTO dto);

	int updateLimitRuleCompanyWithTx(LimitRuleCompanyDTO dto);

	int deleteLimitRuleCompanyWithTx(LimitRuleCompanyDTO dto);
}
	