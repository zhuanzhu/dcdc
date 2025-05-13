package com.egeo.components.order.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.order.po.LimitRuleCompanyPO;
import com.egeo.orm.BaseReadDAO;

public interface LimitRuleCompanyReadDAO extends BaseReadDAO<LimitRuleCompanyPO>{
	/**
	 * 根据限购规则id查询限购规则与公司关系
	 * @param limitRuleId
	 * @return
	 */
	List<Long> findCompanyByLimitRuleId(@Param("limitRuleId")Long limitRuleId);

    Integer findLimitRuleCompanyCount(@Param("po")LimitRuleCompanyPO limitRuleCompanyPO);
}
	