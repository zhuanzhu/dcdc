package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.ShoppingLabelPO;


public interface ShoppingLabelWriteManage {

	Long insertShoppingLabelWithTx(ShoppingLabelPO po);

	int updateShoppingLabelWithTx(ShoppingLabelPO po);

	int deleteShoppingLabelWithTx(ShoppingLabelPO po);
}
	