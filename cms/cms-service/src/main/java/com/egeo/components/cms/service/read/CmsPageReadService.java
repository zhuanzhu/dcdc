package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.CmsPageDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CmsPageReadService {

	public CmsPageDTO findCmsPageById(CmsPageDTO dto);

	public PageResult<CmsPageDTO> findCmsPageOfPage(CmsPageDTO dto,Pagination page);

	public List<CmsPageDTO> findCmsPageAll(CmsPageDTO dto);

	public List<CmsPageDTO> findCmsPageAllByClientType(CmsPageDTO dto);

	int findSupportPageByVersionCode(Long pageId, Long platformId, Integer androidVersionCode, Integer iosVersionCode);
	
	Long findSupportPageByPageTypeAndVersionCode(Integer pageType, Long platformId, Integer androidVersionCode,
			Integer iosVersionCode, Long companyId, Long companyAllId);

	public CmsPageDTO findCmsPageByPageId(CmsPageDTO dto);

	public List<CmsPageDTO> findCmsPageByCompanyIdAndVersion(CmsPageDTO dto);

}
	