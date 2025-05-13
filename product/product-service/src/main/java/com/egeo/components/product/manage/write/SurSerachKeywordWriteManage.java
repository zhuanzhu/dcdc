package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.SurSerachKeywordPO;


public interface SurSerachKeywordWriteManage {

	Long insertSurSerachKeywordWithTx(SurSerachKeywordPO po);

	int updateSurSerachKeywordWithTx(SurSerachKeywordPO po);

	int deleteSurSerachKeywordWithTx(SurSerachKeywordPO po);
}
	