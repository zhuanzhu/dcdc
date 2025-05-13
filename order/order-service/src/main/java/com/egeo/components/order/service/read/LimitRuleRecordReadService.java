package com.egeo.components.order.service.read;


import java.math.BigDecimal;
import java.util.List;

import com.egeo.components.order.dto.LimitRuleRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface LimitRuleRecordReadService {

	public LimitRuleRecordDTO findLimitRuleRecordById(LimitRuleRecordDTO dto);

	public PageResult<LimitRuleRecordDTO> findLimitRuleRecordOfPage(LimitRuleRecordDTO dto,Pagination page);

	public List<LimitRuleRecordDTO> findLimitRuleRecordAll(LimitRuleRecordDTO dto);
	/**
	 * 根据限购规则id查询限购规则购买商品总量
	 * @param id
	 * @return
	 */
	public Long findBuySumByLimitRuleId(Long limitRuleId);
	/**
	 * 根据限购规则id平台id查询限购规则购买商品总量
	 * @param id
	 * @return
	 */
	public Long findBuySumByLimitRuleIdPlatformId(Long limitRuleId,Long platform);
	/**
	 * 根据限购规则id查询限购规则购买商品总金额
	 * @param id
	 * @return
	 */
	public BigDecimal findBuyMoneySumByLimitRuleId(Long limitRuleId);
	/**
	 * 根据用户id和限购规则id平台id查询限购规则记录
	 * @param limitRuleId
	 * @param userId
	 * @param platformId
	 * @return
	 */
	public List<LimitRuleRecordDTO> findByUserIdLimitRuleId(Long limitRuleId, Long userId, Long platformId);
	/**
	 * 根据自然年、月、日类型，用户id，限购规则id，平台id查询限购规则记录信息
	 * @param periodType 周期类型：1、按日限购 2、按月限购 3、按年限购
	 * @param memberId 用户id
	 * @param limitRuleId 限购规则id
	 * @param platformId 平台id
	 * @return
	 */
	public List<LimitRuleRecordDTO> findByPeriodTypeUserIdLimitRuleId(Integer periodType, Long memberId, Long limitRuleId,
			Long platformId);

	/**
	 * 查询限购规则已购买量
	 * @param limitRuleId
	 * @param standardUnitId
	 * @param standardUnitIdList
	 * @param userId
	 * @param storeId
	 * @param companyId
	 * @param periodType
	 * @return
	 */
	LimitRuleRecordDTO selectLimitStatistic(Integer orderStatus,Long limitRuleId, Long standardUnitId,
			List<Long> standardUnitIdList, Long userId, Long storeId, Long companyId, Integer periodType);
}
	