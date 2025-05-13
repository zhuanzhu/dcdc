package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.CmsCfgKeyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CmsCfgKeyReadService {

	public CmsCfgKeyDTO findCmsCfgKeyById(CmsCfgKeyDTO dto);

	public PageResult<CmsCfgKeyDTO> findCmsCfgKeyOfPage(CmsCfgKeyDTO dto,Pagination page);

	public List<CmsCfgKeyDTO> findCmsCfgKeyAll(CmsCfgKeyDTO dto);

	public List<CmsCfgKeyDTO> findElementCfgKeyByTemplateId(Long templateId);
	
	public List<CmsCfgKeyDTO> findTemplateCfgKeyByTemplateId(Long templateId);

}
	