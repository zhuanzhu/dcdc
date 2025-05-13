package com.egeo.components.order.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.order.po.LimitRuleCompanyPO;
import com.egeo.orm.BaseWriteDAO;

public interface LimitRuleCompanyWriteDAO extends BaseWriteDAO<LimitRuleCompanyPO> {
	/**
	 * 批量添加购规则与公司id关系
	 * @param limitRuleCompanyPOs
	 */
	int insertAll(@Param("poList")List<LimitRuleCompanyPO> limitRuleCompanyPOs);
}
	