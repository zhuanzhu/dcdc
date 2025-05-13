package com.egeo.components.cms.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.cms.dto.CmsTemplateDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;	

public interface CmsTemplateManage {

	public CmsTemplateDTO findCmsTemplateById(CmsTemplateDTO dto);	

	public PageResult<CmsTemplateDTO> findCmsTemplateOfPage(CmsTemplateDTO dto,Pagination page,Integer searchType);

	public List<CmsTemplateDTO> findCmsTemplateAll(CmsTemplateDTO dto);

	JsonResult<Long> insertCmsTemplateWithTx(CmsTemplateDTO dto);

	int updateCmsTemplateWithTx(CmsTemplateDTO dto);

	int deleteCmsTemplateWithTx(CmsTemplateDTO dto);

	public List<Map<String, Object>> findCmsElement(Integer groupType);

	public Map<String, Object> findCmsElementById(Long id);

	public int updateStatus(CmsTemplateDTO dto);

	JsonResult<Long> copyCmsTemplateWithTx(Long templateId);
}
	