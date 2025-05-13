package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.NavigationBarPO;


public interface NavigationBarWriteManage {

	Long insertNavigationBarWithTx(NavigationBarPO po);

	int updateNavigationBarWithTx(NavigationBarPO po);

	int deleteNavigationBarWithTx(NavigationBarPO po);
}
	