package com.egeo.components.order.service.read;


import java.util.List;
import java.util.Map;

import com.egeo.components.order.dto.LimitRuleDTO;
import com.egeo.components.order.dto.LimitRuleRecordDTO;
import com.egeo.components.order.dto.LimitRuleUserDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface LimitRuleReadService {

	public LimitRuleDTO findLimitRuleById(LimitRuleDTO dto);

	public PageResult<LimitRuleDTO> findLimitRuleOfPage(LimitRuleDTO dto,Pagination page);

	public List<LimitRuleDTO> findLimitRuleAll(LimitRuleDTO dto);
	/**
	 * 根据su商品id,公司id，平台id查询所有启用限购规则，按创建时间排序
	 * @param standardUnitId
	 * @param companyId
	 * @param platformId
	 * @return
	 */
	public List<String> startLimitRuleByStandardUnitId(Long standardUnitId, Long companyId,Long companyAllId, Long platformId, Long userId,Long storeId, Map<Long, List<Long>> suCombMap);
	/**
	 * 根据su商品id,公司id，平台id查询所有启用限购规则，按创建时间排序
	 * @param standardUnitId
	 * @param companyId
	 * @param platformId
	 * @return
	 */
	public List<LimitRuleDTO> startLimitRuleDTOByStandardUnitId(Long standardUnitId, List<Long> suCombIdList, Long platformId);
	
	/**
	 * 查询限购规则已购买量
	 *
     * @param orderStatus
     * @param limitRuleId
     * @param standardUnitId
     * @param standardUnitIdList
     * @param userId
     * @param storeId
     * @param companyId
     * @param periodType
     * @return
	 */
	LimitRuleRecordDTO selectLimitStatistic(Integer orderStatus, Long limitRuleId, Long standardUnitId,
                                            List<Long> standardUnitIdList, Long userId, Long storeId, Long companyId, Integer periodType);

}
	