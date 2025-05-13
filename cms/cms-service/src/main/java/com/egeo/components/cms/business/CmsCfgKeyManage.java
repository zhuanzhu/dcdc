package com.egeo.components.cms.business;

import java.util.Map;	

public interface CmsCfgKeyManage {

	public Map<String, Object> findCfgKeyByTemplateId(Long templateId, Long pageId);

}
	