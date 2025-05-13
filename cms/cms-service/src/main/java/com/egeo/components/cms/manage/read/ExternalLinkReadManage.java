package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.ExternalLinkPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ExternalLinkReadManage {

	public ExternalLinkPO findExternalLinkById(ExternalLinkPO po);

	public PageResult<ExternalLinkPO> findExternalLinkOfPage(ExternalLinkPO po,Pagination page);

	public List<ExternalLinkPO> findExternalLinkAll(ExternalLinkPO po);
}
	