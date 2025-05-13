package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.ShoppingLabelGroupPO;


public interface ShoppingLabelGroupWriteManage {

	Long insertShoppingLabelGroupWithTx(ShoppingLabelGroupPO po);

	int updateShoppingLabelGroupWithTx(ShoppingLabelGroupPO po);

	int deleteShoppingLabelGroupWithTx(ShoppingLabelGroupPO po);
}
	