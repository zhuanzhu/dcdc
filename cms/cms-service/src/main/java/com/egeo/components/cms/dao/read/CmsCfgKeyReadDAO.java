package com.egeo.components.cms.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.condition.CmsCfgKeyCondition;
import com.egeo.components.cms.po.CmsCfgKeyPO;
import com.egeo.orm.BaseReadDAO;

public interface CmsCfgKeyReadDAO extends BaseReadDAO<CmsCfgKeyPO>{
	
	List<CmsCfgKeyCondition> findElementCfgKeyByTemplateId(@Param("templateId") Long templateId);
	
	List<CmsCfgKeyCondition> findTemplateCfgKeyByTemplateId(@Param("templateId") Long templateId);
	
}
	