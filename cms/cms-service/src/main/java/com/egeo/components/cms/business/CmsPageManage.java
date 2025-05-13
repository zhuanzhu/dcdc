package com.egeo.components.cms.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.cms.dto.CmsPageDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CmsPageManage {

	public CmsPageDTO findCmsPageById(CmsPageDTO dto);	

	public PageResult<Map<String, Object>> findCmsPageOfPage(CmsPageDTO dto,Pagination page);

	public List<CmsPageDTO> findCmsPageAll(CmsPageDTO dto);

	Long insertCmsPageWithTx(CmsPageDTO dto, String configJson);

	int updateCmsPageWithTx(CmsPageDTO dto, String configJson);

	int deleteCmsPageWithTx(CmsPageDTO dto);
	
	Map<String, Object> findPageCfgById(Long pageId, Long clientId, Integer versionCode, Long f, Long companyId, Integer scope, Long platformId, Integer pageType, Pagination page, Long userId);

	
	List<CmsPageDTO> findCmsPageAllByClientType(CmsPageDTO dto);

	public int updateStatus(CmsPageDTO dto);
}
	