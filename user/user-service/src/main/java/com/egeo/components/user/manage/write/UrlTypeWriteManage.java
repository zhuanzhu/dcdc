package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.UrlTypePO;


public interface UrlTypeWriteManage {

	Long insertUrlTypeWithTx(UrlTypePO po);

	int updateUrlTypeWithTx(UrlTypePO po);

	int deleteUrlTypeWithTx(UrlTypePO po);
}
	