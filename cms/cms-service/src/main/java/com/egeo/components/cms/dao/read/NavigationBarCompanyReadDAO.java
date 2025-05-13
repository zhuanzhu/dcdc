package com.egeo.components.cms.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.po.NavigationBarCompanyPO;
import com.egeo.orm.BaseReadDAO;

public interface NavigationBarCompanyReadDAO extends BaseReadDAO<NavigationBarCompanyPO>{

	/**
	 * 通过公司id集合查询首页导航栏
	 * @param companyIdList
	 * @return
	 */
	List<NavigationBarCompanyPO> findPageTabAllByCompanyId(@Param("companyIdList")List<Long> companyIdList);
}
	