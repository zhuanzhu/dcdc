package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.CmsTemplatePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CmsTemplateReadManage {

	public CmsTemplatePO findCmsTemplateById(CmsTemplatePO po);

	public PageResult<CmsTemplatePO> findCmsTemplateOfPage(CmsTemplatePO po,Pagination page,Integer searchType);

	public List<CmsTemplatePO> findCmsTemplateAll(CmsTemplatePO po);

	public List<CmsTemplatePO> findCmsTemplateAll(CmsTemplatePO po, Integer searchType);

}
	