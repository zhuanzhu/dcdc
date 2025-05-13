package com.egeo.components.promotion.manage.write;

import com.egeo.components.promotion.po.CardUseRecordPO;

import java.math.BigDecimal;


public interface CardUseRecordWriteManage {

	Long insertCardUseRecordWithTx(CardUseRecordPO po);

	int updateCardUseRecordWithTx(CardUseRecordPO po);

	int deleteCardUseRecordWithTx(CardUseRecordPO po);

	public int cancelUserCard();
}
