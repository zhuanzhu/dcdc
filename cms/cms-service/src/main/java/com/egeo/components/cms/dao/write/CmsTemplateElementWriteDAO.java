package com.egeo.components.cms.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.po.CmsTemplateElementPO;
import com.egeo.orm.BaseWriteDAO;

public interface CmsTemplateElementWriteDAO extends BaseWriteDAO<CmsTemplateElementPO> {

	int insertBatch(@Param("pos") List<CmsTemplateElementPO> pos);
}
	