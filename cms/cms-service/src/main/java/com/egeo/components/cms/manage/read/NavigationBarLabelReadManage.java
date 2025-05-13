package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.NavigationBarLabelPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface NavigationBarLabelReadManage {

	public NavigationBarLabelPO findNavigationBarLabelById(NavigationBarLabelPO po);

	public PageResult<NavigationBarLabelPO> findNavigationBarLabelOfPage(NavigationBarLabelPO po,Pagination page);

	public List<NavigationBarLabelPO> findNavigationBarLabelAll(NavigationBarLabelPO po);
}
	