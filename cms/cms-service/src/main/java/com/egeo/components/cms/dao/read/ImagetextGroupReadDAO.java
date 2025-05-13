package com.egeo.components.cms.dao.read;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.po.ImagetextGroupPO;
import com.egeo.orm.BaseReadDAO;

public interface ImagetextGroupReadDAO extends BaseReadDAO<ImagetextGroupPO>{

	/**
	 * 根据实例id查询图文组件组
	 * @param instId
	 * @return
	 */
	ImagetextGroupPO queryImagetextGroupByInstId(@Param("instId")Long instId, @Param("groupType")Integer groupType);
}
	