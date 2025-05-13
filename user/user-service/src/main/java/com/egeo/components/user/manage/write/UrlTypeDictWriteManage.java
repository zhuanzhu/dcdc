package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.UrlTypeDictPO;


public interface UrlTypeDictWriteManage {

	Long insertUrlTypeDictWithTx(UrlTypeDictPO po);

	int updateUrlTypeDictWithTx(UrlTypeDictPO po);

	int deleteUrlTypeDictWithTx(UrlTypeDictPO po);
}
	