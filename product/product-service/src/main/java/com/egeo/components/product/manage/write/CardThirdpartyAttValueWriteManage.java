package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.CardThirdpartyAttValuePO;


public interface CardThirdpartyAttValueWriteManage {

	Long insertCardThirdpartyAttValueWithTx(CardThirdpartyAttValuePO po);

	int updateCardThirdpartyAttValueWithTx(CardThirdpartyAttValuePO po);

	int deleteCardThirdpartyAttValueWithTx(CardThirdpartyAttValuePO po);
}
	