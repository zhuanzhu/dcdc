package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.LinkableParamPO;


public interface LinkableParamWriteManage {

	Long insertLinkableParamWithTx(LinkableParamPO po);

	int updateLinkableParamWithTx(LinkableParamPO po);

	int deleteLinkableParamWithTx(LinkableParamPO po);

	int deleteLinkableParamByParamWithTx(LinkableParamPO po);
}
	