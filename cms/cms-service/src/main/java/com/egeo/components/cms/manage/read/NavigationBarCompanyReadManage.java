package com.egeo.components.cms.manage.read;

import java.util.List;

import com.egeo.components.cms.po.NavigationBarCompanyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface NavigationBarCompanyReadManage {

	public NavigationBarCompanyPO findNavigationBarCompanyById(NavigationBarCompanyPO po);

	public PageResult<NavigationBarCompanyPO> findNavigationBarCompanyOfPage(NavigationBarCompanyPO po,Pagination page);

	public List<NavigationBarCompanyPO> findNavigationBarCompanyAll(NavigationBarCompanyPO po);

	/**
	 * 通过公司id集合查询首页导航栏
	 * @param companyIdList
	 * @return
	 */
	public List<NavigationBarCompanyPO> findPageTabAllByCompanyId(List<Long> companyIdList);
}
	