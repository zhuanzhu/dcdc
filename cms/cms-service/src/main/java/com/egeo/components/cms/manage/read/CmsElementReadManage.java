package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.CmsElementPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CmsElementReadManage {

	public CmsElementPO findCmsElementById(CmsElementPO po);

	public PageResult<CmsElementPO> findCmsElementOfPage(CmsElementPO po,Pagination page);

	public List<CmsElementPO> findCmsElementAll(CmsElementPO po);
	
	public List<CmsElementPO> findCmsElementByTemplateId(Long templateId);

	public List<CmsElementPO> findMaxVersionByElementIdList(List<Long> elementIdList);
}
	