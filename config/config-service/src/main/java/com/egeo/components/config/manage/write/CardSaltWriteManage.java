package com.egeo.components.config.manage.write;

import com.egeo.components.config.po.CardSaltPO;


public interface CardSaltWriteManage {

	Long insertCardSaltWithTx(CardSaltPO po);

	int updateCardSaltWithTx(CardSaltPO po);

	int deleteCardSaltWithTx(CardSaltPO po);
}
	