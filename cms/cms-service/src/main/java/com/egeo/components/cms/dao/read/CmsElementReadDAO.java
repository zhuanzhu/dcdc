package com.egeo.components.cms.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.po.CmsElementPO;
import com.egeo.orm.BaseReadDAO;

public interface CmsElementReadDAO extends BaseReadDAO<CmsElementPO>{

	List<CmsElementPO> findCmsElementByTemplateId(@Param("templateId") Long templateId);

	List<CmsElementPO> findCmsElementByIdList(@Param("ids") List<Long> ids);
}
	