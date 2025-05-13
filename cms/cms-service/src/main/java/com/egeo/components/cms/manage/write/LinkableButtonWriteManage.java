package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.LinkableButtonPO;


public interface LinkableButtonWriteManage {

	Long insertLinkableButtonWithTx(LinkableButtonPO po);

	int updateLinkableButtonWithTx(LinkableButtonPO po);

	int deleteLinkableButtonWithTx(LinkableButtonPO po);
}
	