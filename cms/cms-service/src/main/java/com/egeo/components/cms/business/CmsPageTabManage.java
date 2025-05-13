package com.egeo.components.cms.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.cms.dto.CmsPageTabDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CmsPageTabManage {

	public CmsPageTabDTO findCmsPageTabById(CmsPageTabDTO dto);	

	public PageResult<CmsPageTabDTO> findCmsPageTabOfPage(CmsPageTabDTO dto,Pagination page);

	public List<CmsPageTabDTO> findCmsPageTabAll(CmsPageTabDTO dto);

	Long insertCmsPageTabWithTx(CmsPageTabDTO dto);

	void updateCmsPageTabWithTx(List<CmsPageTabDTO> cmsPageTabDTOS,Long platformId);

	int deleteCmsPageTabWithTx(CmsPageTabDTO dto);

	List<Map<String, Object>> findCmsPageTabFront(CmsPageTabDTO dto);
}
	