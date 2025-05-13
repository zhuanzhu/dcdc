package com.egeo.components.user.manage.write;

import java.util.List;

import com.egeo.components.user.po.UrlWhiteListPO;


public interface UrlWhiteListWriteManage {

	Long insertUrlWhiteListWithTx(UrlWhiteListPO po, List<Long> platformIdList);

	int updateUrlWhiteListWithTx(UrlWhiteListPO po, List<Long> platformIdList);

	int deleteUrlWhiteListWithTx(UrlWhiteListPO po);
}
	