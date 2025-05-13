package com.egeo.components.cms.service.write;

import java.util.List;

import com.egeo.components.cms.dto.CmsTemplateCfgDTO;


public interface CmsTemplateCfgWriteService {

	public Long insertCmsTemplateCfgWithTx(CmsTemplateCfgDTO dto);

	public int updateCmsTemplateCfgWithTx(CmsTemplateCfgDTO dto);

	public int deleteCmsTemplateCfgWithTx(CmsTemplateCfgDTO dto);
	
	/**
	 * 批量插入
	 * @param dtos
	 * @return
	 */
	public int insertBatchWithTx(List<CmsTemplateCfgDTO> dtos);
	
}
	