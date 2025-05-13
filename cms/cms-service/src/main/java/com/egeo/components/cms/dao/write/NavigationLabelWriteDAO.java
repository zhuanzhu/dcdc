package com.egeo.components.cms.dao.write;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.po.NavigationLabelPO;
import com.egeo.orm.BaseWriteDAO;

public interface NavigationLabelWriteDAO extends BaseWriteDAO<NavigationLabelPO> {

	/**
	 * 删除导航栏与导航栏标签的关系
	 * @param navigationLabelPO
	 */
	void resetNavigationLabelRel(@Param("po")NavigationLabelPO navigationLabelPO);
}
	