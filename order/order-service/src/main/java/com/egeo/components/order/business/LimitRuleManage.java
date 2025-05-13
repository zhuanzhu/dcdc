package com.egeo.components.order.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.order.dto.LimitRuleDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface LimitRuleManage {
	/**
	 * 根据限购规则id查询限购规则信息
	 * @param dto
	 * @return
	 */
	public Map<String, Object> findLimitRuleById(Long limitRuleId);	

	public PageResult<Map<String, Object>> findLimitRuleOfPage(LimitRuleDTO dto,Pagination page);

	public List<LimitRuleDTO> findLimitRuleAll(LimitRuleDTO dto);

	Long insertLimitRuleWithTx(LimitRuleDTO dto, List<Long> companyIdList, List<Long> userCompanyIdList, List<Long> storeIdList);

	int updateLimitRuleWithTx(LimitRuleDTO dto, List<Long> companyIdList, List<Long> userCompanyIdList, List<Long> storeIdList);

	int deleteLimitRuleWithTx(LimitRuleDTO dto);
	/**
	 * 根据限购规则id启用停用限购规则
	 * @param limitRuleId
	 * @param isStart
	 * @return
	 */
	public int isLimitRuleStartWithTx(Long limitRuleId, Integer isStart);
	/**
	 * 根据su商品id,公司id，平台id查询所有启用限购规则，按创建时间排序
	 * @param standardUnitId
	 * @param companyId
	 * @param platformId
	 * @return
	 */
	public Map<String, Object> startLimitRuleByStandardUnitId(Long standardUnitId,Long companyId,Long platformId, Long userId, Long storeId);
}
	