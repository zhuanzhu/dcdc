package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.LocalParamPO;


public interface LocalParamWriteManage {

	Long insertLocalParamWithTx(LocalParamPO po);

	int updateLocalParamWithTx(LocalParamPO po);

	int deleteLocalParamWithTx(LocalParamPO po);
}
	