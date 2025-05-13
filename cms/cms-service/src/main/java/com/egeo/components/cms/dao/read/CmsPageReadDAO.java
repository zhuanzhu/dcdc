package com.egeo.components.cms.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.condition.CmsPageCondition;
import com.egeo.components.cms.po.CmsPagePO;
import com.egeo.components.cms.po.CmsTemplatePO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface CmsPageReadDAO extends BaseReadDAO<CmsPagePO>{

	List<CmsPageCondition> findAllByClientType(@Param("condition")CmsPageCondition condition);
	
	int findSupportPageByVersionCode(@Param("pageId")Long pageId, @Param("platformId") Long platformId, 
			@Param("androidVersionCode") Integer androidVersionCode, @Param("iosVersionCode") Integer iosVersionCode);
	
	Long findSupportPageByPageTypeAndVersionCode(@Param("pageType")Integer pageType, @Param("platformId") Long platformId, 
			@Param("androidVersionCode") Integer androidVersionCode, @Param("iosVersionCode") Integer iosVersionCode, 
			@Param("companyId")Long companyId, @Param("companyAllId")Long companyAllId);


	int countBlurOfPage(@Param("condition") CmsPageCondition condition);

	List<CmsPageCondition> findBlurOfPage(@Param("condition") CmsPageCondition condition,@Param("page") Pagination page);

	CmsPageCondition findCmsPageByPageId(@Param("po")CmsPagePO po);

	List<CmsPageCondition> findCmsPageByCompanyIdAndVersion(@Param("condition") CmsPageCondition condition);
}
	