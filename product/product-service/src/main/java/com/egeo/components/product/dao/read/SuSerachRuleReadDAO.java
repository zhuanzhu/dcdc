package com.egeo.components.product.dao.read;

import com.egeo.components.product.po.SuSerachRulePO;
import com.egeo.orm.BaseReadDAO;
import org.apache.ibatis.annotations.Param;

public interface SuSerachRuleReadDAO extends BaseReadDAO<SuSerachRulePO>{
    Integer findSuSerachRuleListSize(@Param("id")Long id);
}
	