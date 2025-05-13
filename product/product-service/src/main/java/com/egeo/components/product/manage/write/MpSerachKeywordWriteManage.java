package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.MpSerachKeywordPO;


public interface MpSerachKeywordWriteManage {

	Long insertMpSerachKeywordWithTx(MpSerachKeywordPO po);

	int updateMpSerachKeywordWithTx(MpSerachKeywordPO po);

	int deleteMpSerachKeywordWithTx(MpSerachKeywordPO po);
}
	