package com.egeo.components.order.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.order.po.LimitRulePO;
import com.egeo.orm.BaseReadDAO;

public interface LimitRuleReadDAO extends BaseReadDAO<LimitRulePO>{
	/**
	 * 根据su商品id,公司id，平台id查询所有启用限购规则，按创建时间排序
	 * @param standardUnitId
	 * @param companyId
	 * @param platformId
	 * @return
	 */
	List<LimitRulePO> startLimitRuleByStandardUnitId(
			@Param("standardUnitId")Long standardUnitId,
			@Param("suCombIdList")List<Long> suCombIdList,
			@Param("platformId")Long platformId);
}
	