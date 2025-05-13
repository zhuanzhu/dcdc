package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.ExternalLinkPO;


public interface ExternalLinkWriteManage {

	Long insertExternalLinkWithTx(ExternalLinkPO po);

	int updateExternalLinkWithTx(ExternalLinkPO po);

	int deleteExternalLinkWithTx(ExternalLinkPO po);
}
	