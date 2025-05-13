package com.egeo.components.cms.manage.write;

import java.util.List;

import com.egeo.components.cms.po.LinkableButtonPagePO;


public interface LinkableButtonPageWriteManage {

	Long insertLinkableButtonPageWithTx(LinkableButtonPagePO po);

	int updateLinkableButtonPageWithTx(LinkableButtonPagePO po);

	int deleteLinkableButtonPageWithTx(LinkableButtonPagePO po);
	
	int insertBatchLinkableButtonPageWithTx(List<LinkableButtonPagePO> pos);

	int deleteLinkableButtonPageByLinkableIdWithTx(LinkableButtonPagePO po);
}
	