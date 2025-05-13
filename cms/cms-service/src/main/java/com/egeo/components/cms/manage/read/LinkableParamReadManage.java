package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.LinkableParamPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface LinkableParamReadManage {

	public LinkableParamPO findLinkableParamById(LinkableParamPO po);

	public PageResult<LinkableParamPO> findLinkableParamOfPage(LinkableParamPO po,Pagination page);

	public List<LinkableParamPO> findLinkableParamAll(LinkableParamPO po);
}
	