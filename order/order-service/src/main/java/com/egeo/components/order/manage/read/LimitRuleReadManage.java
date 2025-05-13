package com.egeo.components.order.manage.read;

import java.util.List;
	
import com.egeo.components.order.po.LimitRulePO;
import com.egeo.components.order.po.LimitRuleRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface LimitRuleReadManage {

	public LimitRulePO findLimitRuleById(LimitRulePO po);

	public PageResult<LimitRulePO> findLimitRuleOfPage(LimitRulePO po,Pagination page);

	public List<LimitRulePO> findLimitRuleAll(LimitRulePO po);
	/**
	 * 根据su商品id,公司id，平台id查询所有启用限购规则，按创建时间排序
	 * @param standardUnitId
	 * @param companyId
	 * @param platformId
	 * @return
	 */
	public List<LimitRulePO> startLimitRuleByStandardUnitId(Long standardUnitId, List<Long> suCombIdList, Long platformId);
	
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
	