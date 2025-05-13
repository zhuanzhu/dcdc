package com.egeo.components.order.dao.read;

import com.egeo.components.order.po.LimitRuleUserPO;
import com.egeo.orm.BaseReadDAO;
import org.apache.ibatis.annotations.Param;

public interface LimitRuleUserReadDAO extends BaseReadDAO<LimitRuleUserPO>{
    Integer findLimitRuleAllByParam(@Param("po")LimitRuleUserPO limitRuleUserPO);
}
	