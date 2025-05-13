package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.NavigationBarLabelPO;


public interface NavigationBarLabelWriteManage {

	Long insertNavigationBarLabelWithTx(NavigationBarLabelPO po);

	int updateNavigationBarLabelWithTx(NavigationBarLabelPO po);

	int deleteNavigationBarLabelWithTx(NavigationBarLabelPO po);
}
	