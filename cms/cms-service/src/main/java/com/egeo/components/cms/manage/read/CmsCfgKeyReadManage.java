package com.egeo.components.cms.manage.read;

import java.util.List;

import com.egeo.components.cms.condition.CmsCfgKeyCondition;
import com.egeo.components.cms.po.CmsCfgKeyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CmsCfgKeyReadManage {

	public CmsCfgKeyPO findCmsCfgKeyById(CmsCfgKeyPO po);

	public PageResult<CmsCfgKeyPO> findCmsCfgKeyOfPage(CmsCfgKeyPO po,Pagination page);

	public List<CmsCfgKeyPO> findCmsCfgKeyAll(CmsCfgKeyPO po);
	
	List<CmsCfgKeyCondition> findElementCfgKeyByTemplateId(Long templateId);
	
	List<CmsCfgKeyCondition> findTemplateCfgKeyByTemplateId(Long templateId);
	
}
	