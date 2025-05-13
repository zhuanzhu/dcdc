package com.egeo.components.config.manage.write;

import com.egeo.components.config.po.KeywordPO;


public interface KeywordWriteManage {

	Long insertKeywordWithTx(KeywordPO po);

	int updateKeywordWithTx(KeywordPO po);

	int deleteKeywordWithTx(KeywordPO po);
}
	