package com.egeo.components.order.dao.read;

import com.egeo.components.order.po.LimitRuleStorePO;
import com.egeo.orm.BaseReadDAO;
import org.apache.ibatis.annotations.Param;

public interface LimitRuleStoreReadDAO extends BaseReadDAO<LimitRuleStorePO>{
    Integer findLimitRuleStoreCount(@Param("po")LimitRuleStorePO limitRuleStorePO);
}
	