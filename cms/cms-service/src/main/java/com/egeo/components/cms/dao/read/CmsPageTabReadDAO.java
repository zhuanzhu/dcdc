package com.egeo.components.cms.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.condition.CmsPageTabCondition;
import com.egeo.components.cms.po.CmsPageTabPO;
import com.egeo.orm.BaseReadDAO;

public interface CmsPageTabReadDAO extends BaseReadDAO<CmsPageTabPO>{

	List<CmsPageTabCondition> findCmsPageTabCondition(@Param("po") CmsPageTabCondition condition);

	List<CmsPageTabCondition> findDefaultCmsPageTabFront(@Param("platformId") Long platformId);
}
	