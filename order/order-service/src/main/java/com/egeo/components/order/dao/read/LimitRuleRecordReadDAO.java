package com.egeo.components.order.dao.read;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.order.po.LimitRuleRecordPO;
import com.egeo.orm.BaseReadDAO;

public interface LimitRuleRecordReadDAO extends BaseReadDAO<LimitRuleRecordPO>{
	/**
	 * 根据限购规则id查询限购规则购买商品总量
	 * @param id
	 * @return
	 */
	Long findBuySumByLimitRuleId(@Param("limitRuleId")Long limitRuleId);
	/**
	 * 根据限购规则id平台id查询限购规则购买商品总量
	 * @param id
	 * @return
	 */
	Long findBuySumByLimitRuleIdPlatformId(@Param("limitRuleId")Long limitRuleId,@Param("platformId")Long platformId);
	/**
	 * 根据限购规则id查询限购规则购买商品总金额
	 * @param id
	 * @return
	 */
	BigDecimal findBuyMoneySumByLimitRuleId(@Param("limitRuleId")Long limitRuleId);
	/**
	 * 根据自然年、月、日类型，用户id，限购规则id，平台id查询限购规则记录信息
	 * @param periodType 周期类型：1、按日限购 2、按月限购 3、按年限购
	 * @param memberId 用户id
	 * @param limitRuleId 限购规则id
	 * @param platformId 平台id
	 * @return
	 */
	List<LimitRuleRecordPO> findByPeriodTypeUserIdLimitRuleId(@Param("periodType")Integer periodType, @Param("userId")Long memberId, @Param("limitRuleId")Long limitRuleId,
			@Param("platformId")Long platformId);
	
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
	LimitRuleRecordPO selectLimitStatistic(@Param("orderStatus")Integer orderStatus, @Param("limitRuleId") Long limitRuleId, @Param("standardUnitId") Long standardUnitId,
                                           @Param("standardUnitIdList") List<Long> standardUnitIdList, @Param("userId") Long userId, @Param("storeId") Long storeId,
                                           @Param("companyId") Long companyId, @Param("periodType") Integer periodType);
}
