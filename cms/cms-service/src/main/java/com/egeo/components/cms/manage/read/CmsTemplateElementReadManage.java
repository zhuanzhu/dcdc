package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.CmsTemplateElementPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CmsTemplateElementReadManage {

	public CmsTemplateElementPO findCmsTemplateElementById(CmsTemplateElementPO po);

	public PageResult<CmsTemplateElementPO> findCmsTemplateElementOfPage(CmsTemplateElementPO po,Pagination page);

	public List<CmsTemplateElementPO> findCmsTemplateElementAll(CmsTemplateElementPO po);
}
	