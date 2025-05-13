package com.egeo.components.cms.manage.read;

import java.util.List;

import com.egeo.components.cms.condition.LinkableButtonPageCondition;
import com.egeo.components.cms.po.LinkableButtonPagePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface LinkableButtonPageReadManage {

	public LinkableButtonPagePO findLinkableButtonPageById(LinkableButtonPagePO po);

	public PageResult<LinkableButtonPagePO> findLinkableButtonPageOfPage(LinkableButtonPagePO po,Pagination page);

	public List<LinkableButtonPagePO> findLinkableButtonPageAll(LinkableButtonPagePO po);

	public List<LinkableButtonPageCondition> findCmsPageByLinkableId(Long linkableId);
}
	