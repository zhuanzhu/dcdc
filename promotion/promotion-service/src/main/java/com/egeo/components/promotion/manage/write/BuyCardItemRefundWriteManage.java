package com.egeo.components.promotion.manage.write;

import com.egeo.components.promotion.po.BuyCardItemRefundPO;


public interface BuyCardItemRefundWriteManage {

	Long insertBuyCardItemRefundWithTx(BuyCardItemRefundPO po);

	int updateBuyCardItemRefundWithTx(BuyCardItemRefundPO po);

	int deleteBuyCardItemRefundWithTx(BuyCardItemRefundPO po);
}

