package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.NavigationBarPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface NavigationBarReadManage {

	public NavigationBarPO findNavigationBarById(NavigationBarPO po);

	public PageResult<NavigationBarPO> findNavigationBarOfPage(NavigationBarPO po,Pagination page);

	public List<NavigationBarPO> findNavigationBarAll(NavigationBarPO po);

	/**
	 * 模糊查询导航栏列表
	 * @param po
	 * @param page
	 * @return
	 */
	public PageResult<NavigationBarPO> findNavigationBarOfPageByBlurry(NavigationBarPO po, Pagination page);
}
	