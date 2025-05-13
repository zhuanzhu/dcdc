package com.egeo.components.cms.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.condition.CmsInstCfgCondition;
import com.egeo.components.cms.po.CmsInstCfgPO;
import com.egeo.orm.BaseReadDAO;

public interface CmsInstCfgReadDAO extends BaseReadDAO<CmsInstCfgPO>{

	/**
	 * 根据页面ID查询配置信息
	 * @param pageId
	 * @return
	 */
	List<CmsInstCfgCondition> findPageInstCfgByPageId(@Param("pageId")Long pageId, @Param("instIdList")List<Long> instIdList);
	
}
	