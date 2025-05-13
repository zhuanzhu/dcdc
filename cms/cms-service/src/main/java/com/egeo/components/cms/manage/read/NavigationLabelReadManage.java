package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.NavigationLabelPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface NavigationLabelReadManage {

	public NavigationLabelPO findNavigationLabelById(NavigationLabelPO po);

	public PageResult<NavigationLabelPO> findNavigationLabelOfPage(NavigationLabelPO po,Pagination page);

	public List<NavigationLabelPO> findNavigationLabelAll(NavigationLabelPO po);
}
	