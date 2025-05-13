package com.egeo.components.order.service.read;


import java.util.List;
	
import com.egeo.components.order.dto.LimitRuleCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface LimitRuleCompanyReadService {

	public LimitRuleCompanyDTO findLimitRuleCompanyById(LimitRuleCompanyDTO dto);

	public PageResult<LimitRuleCompanyDTO> findLimitRuleCompanyOfPage(LimitRuleCompanyDTO dto,Pagination page);

	public List<LimitRuleCompanyDTO> findLimitRuleCompanyAll(LimitRuleCompanyDTO dto);
	/**
	 * 根据限购规则id查询限购规则与公司关系
	 * @param limitRuleId
	 * @return
	 */
	public List<Long> findCompanyByLimitRuleId(Long limitRuleId);

    Integer findLimitRuleCompanyCount(LimitRuleCompanyDTO limitRuleCompanyDTO);
}
	