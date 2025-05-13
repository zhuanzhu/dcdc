package com.egeo.components.promotion.manage.write;

import com.egeo.components.promotion.po.BuyCardItemPO;


public interface BuyCardItemWriteManage {

	Long insertBuyCardItemWithTx(BuyCardItemPO po);

	int updateBuyCardItemWithTx(BuyCardItemPO po);

	int deleteBuyCardItemWithTx(BuyCardItemPO po);
}
