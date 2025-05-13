package com.egeo.components.order.manage.read;

import java.math.BigDecimal;
import java.util.List;

import com.egeo.components.order.po.LimitRuleRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface LimitRuleRecordReadManage {

	public LimitRuleRecordPO findLimitRuleRecordById(LimitRuleRecordPO po);

	public PageResult<LimitRuleRecordPO> findLimitRuleRecordOfPage(LimitRuleRecordPO po,Pagination page);

	public List<LimitRuleRecordPO> findLimitRuleRecordAll(LimitRuleRecordPO po);
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
	public Long findBuySumByLimitRuleIdPlatformId(Long limitRuleId,Long platformId);
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
	public List<LimitRuleRecordPO> findByUserIdLimitRuleId(Long limitRuleId, Long userId, Long platformId);
	/**
	 * 根据自然年、月、日类型，用户id，限购规则id，平台id查询限购规则记录信息
	 * @param periodType 周期类型：1、按日限购 2、按月限购 3、按年限购
	 * @param memberId 用户id
	 * @param limitRuleId 限购规则id
	 * @param platformId 平台id
	 * @return
	 */
	public List<LimitRuleRecordPO> findByPeriodTypeUserIdLimitRuleId(Integer periodType, Long memberId,
			Long limitRuleId, Long platformId);
	
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
	LimitRuleRecordPO selectLimitStatistic(Integer orderStatus, Long limitRuleId, Long standardUnitId,
                                           List<Long> standardUnitIdList, Long userId, Long storeId, Long companyId, Integer periodType);

}
	