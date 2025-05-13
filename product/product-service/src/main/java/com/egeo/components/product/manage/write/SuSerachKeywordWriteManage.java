package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.SuSerachKeywordPO;


public interface SuSerachKeywordWriteManage {

	Long insertSuSerachKeywordWithTx(SuSerachKeywordPO po);

	int updateSuSerachKeywordWithTx(SuSerachKeywordPO po);

	int deleteSuSerachKeywordWithTx(SuSerachKeywordPO po);
}
	