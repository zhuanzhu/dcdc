package com.egeo.components.cms.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.po.CmsTemplateCfgPO;
import com.egeo.orm.BaseWriteDAO;

public interface CmsTemplateCfgWriteDAO extends BaseWriteDAO<CmsTemplateCfgPO> {

	int insertBatch(@Param("pos") List<CmsTemplateCfgPO> pos);
}
	