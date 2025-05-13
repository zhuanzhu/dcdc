package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.LinkableButtonPO;
import com.egeo.components.cms.po.NavigationLabelPO;


public interface NavigationLabelWriteManage {

	Long insertNavigationLabelWithTx(NavigationLabelPO po);

	int updateNavigationLabelWithTx(NavigationLabelPO po);

	int deleteNavigationLabelWithTx(NavigationLabelPO po);

	/**
	 * 新增或编辑导航栏标签
	 * @param po
	 * @param linkableButtonPO
	 * @return
	 */
	Long insertOrUpdateNavigationLableWithTx(NavigationLabelPO po, LinkableButtonPO linkableButtonPO);
}
	