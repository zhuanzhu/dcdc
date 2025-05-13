package com.egeo.components.cms.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.condition.LinkableButtonPageCondition;
import com.egeo.components.cms.po.LinkableButtonPagePO;
import com.egeo.orm.BaseReadDAO;

public interface LinkableButtonPageReadDAO extends BaseReadDAO<LinkableButtonPagePO>{

	List<LinkableButtonPageCondition> findCmsPageByLinkableId(@Param("linkableId") Long linkableId);
}
	