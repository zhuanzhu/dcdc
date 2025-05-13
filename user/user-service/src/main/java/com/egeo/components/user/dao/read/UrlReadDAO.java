package com.egeo.components.user.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.condition.UrlCondition;
import com.egeo.components.user.po.UrlPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface UrlReadDAO extends BaseReadDAO<UrlPO>{

	List<String> getUrlListByUserId(@Param("userId")Long userId, @Param("platformId")Long platformId);
	
	List<String> getFunctionUrlListByUserId(@Param("userId")Long userId, @Param("platformId")Long platformId);

    List<UrlPO> getUrlByRoleId(Long roleId);
    /**
     * 根据类型查询url信息
     * @param type
     * @return
     */
	List<UrlPO> findByType(@Param("type")Integer type,@Param("platformId")Long platformId);
	
	List<UrlCondition> findUrlConditionOfPage(@Param("po") UrlPO po, @Param("page") Pagination page);
}
	