package com.egeo.components.cms.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.po.IconPO;
import com.egeo.orm.BaseReadDAO;

public interface IconReadDAO extends BaseReadDAO<IconPO>{

	/**
	 * 根据组id查询icon列表
	 * @param groupId
	 * @return
	 */
	List<IconPO> queryIconsByGroupId(@Param("groupId")Long groupId);
}
	