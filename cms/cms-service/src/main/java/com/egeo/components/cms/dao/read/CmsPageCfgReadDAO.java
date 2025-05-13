package com.egeo.components.cms.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.condition.CmsPageCfgCondition;
import com.egeo.components.cms.po.CmsPageCfgPO;
import com.egeo.orm.BaseReadDAO;

public interface CmsPageCfgReadDAO extends BaseReadDAO<CmsPageCfgPO>{
	
	List<CmsPageCfgCondition> findPageCfgByPageId(@Param("pageId")Long pageId);
	
}
	