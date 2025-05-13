package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.NavigationBarCompanyPO;


public interface NavigationBarCompanyWriteManage {

	Long insertNavigationBarCompanyWithTx(NavigationBarCompanyPO po);

	int updateNavigationBarCompanyWithTx(NavigationBarCompanyPO po);

	int deleteNavigationBarCompanyWithTx(NavigationBarCompanyPO po);
}
	