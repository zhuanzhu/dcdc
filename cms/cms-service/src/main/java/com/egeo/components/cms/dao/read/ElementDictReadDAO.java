package com.egeo.components.cms.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.po.ElementDictPO;
import com.egeo.orm.BaseReadDAO;

public interface ElementDictReadDAO extends BaseReadDAO<ElementDictPO>{

	/**
	 * 根据类型查询组件字典
	 * @param configType
	 * @return
	 */
	ElementDictPO queryElementDictByConfigType(@Param("configType")Integer configType);

	/**
	 * 查询非此类组件字典列表
	 * @param type
	 * @return
	 */
	List<ElementDictPO> queryElementDictByNotType(@Param("type")Integer type);
}
	