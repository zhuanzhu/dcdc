package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.LinkableButtonPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface LinkableButtonReadManage {

	public LinkableButtonPO findLinkableButtonById(LinkableButtonPO po);

	public PageResult<LinkableButtonPO> findLinkableButtonOfPage(LinkableButtonPO po,Pagination page);

	public List<LinkableButtonPO> findLinkableButtonAll(LinkableButtonPO po);
}
	