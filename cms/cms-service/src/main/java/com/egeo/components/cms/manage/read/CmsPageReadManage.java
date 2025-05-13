package com.egeo.components.cms.manage.read;

import java.util.List;

import com.egeo.components.cms.condition.CmsPageCondition;
import com.egeo.components.cms.po.CmsPagePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CmsPageReadManage {

	public CmsPagePO findCmsPageById(CmsPagePO po);

	public List<CmsPagePO> findCmsPageAll(CmsPagePO po);

	public List<CmsPageCondition> findCmsPageAllByClientType(CmsPageCondition condition);
	
	int findSupportPageByVersionCode(Long pageId, Long platformId, Integer androidVersionCode, Integer iosVersionCode);
	
	Long findSupportPageByPageTypeAndVersionCode(Integer pageType, Long platformId, Integer androidVersionCode, Integer iosVersionCode, Long companyId, Long companyAllId);

	public PageResult<CmsPageCondition> findCmsPageOfPage(CmsPageCondition po, Pagination page);

	public CmsPageCondition findCmsPageByPageId(CmsPagePO po);

	public List<CmsPageCondition> findCmsPageByCompanyIdAndVersion(CmsPageCondition condition);
}
	