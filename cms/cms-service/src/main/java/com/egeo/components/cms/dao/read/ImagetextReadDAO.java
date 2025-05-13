package com.egeo.components.cms.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.po.ImagetextPO;
import com.egeo.orm.BaseReadDAO;

public interface ImagetextReadDAO extends BaseReadDAO<ImagetextPO>{

	/**
	 * 根据组id查询图文组件
	 * @param groupId
	 * @return
	 */
	List<ImagetextPO> queryImagetextByGroupId(@Param("groupId")Long groupId);
}
	