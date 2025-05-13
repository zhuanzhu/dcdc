package com.egeo.components.user.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.condition.UrlWhiteListCondition;
import com.egeo.components.user.po.UrlWhiteListPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface UrlWhiteListReadDAO extends BaseReadDAO<UrlWhiteListPO> {
	
	List<UrlWhiteListCondition> findAllUrlWhiteListCondition(@Param("po") UrlWhiteListPO po , @Param("page") Pagination page);
	
	List<UrlWhiteListCondition> findUrlWhiteListConditionOfPage(@Param("po") UrlWhiteListPO po, @Param("page") Pagination page);

	List<String> findUrlWhiteList(@Param("platformId") Long platformId);

}
	
