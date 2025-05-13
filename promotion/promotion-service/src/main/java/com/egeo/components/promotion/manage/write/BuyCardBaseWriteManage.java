package com.egeo.components.promotion.manage.write;

import com.egeo.components.promotion.po.BuyCardBasePO;


public interface BuyCardBaseWriteManage {

	Long insertBuyCardBaseWithTx(BuyCardBasePO po);

	int updateBuyCardBaseWithTx(BuyCardBasePO po);

	int deleteBuyCardBaseWithTx(BuyCardBasePO po);
}
