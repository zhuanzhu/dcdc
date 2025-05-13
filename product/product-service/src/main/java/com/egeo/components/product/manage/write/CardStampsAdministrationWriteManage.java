package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.CardStampsAdministrationPO;


public interface CardStampsAdministrationWriteManage {

	Long insertCardStampsAdministrationWithTx(CardStampsAdministrationPO po);

	int updateCardStampsAdministrationWithTx(CardStampsAdministrationPO po);

	int deleteCardStampsAdministrationWithTx(CardStampsAdministrationPO po);
}
	