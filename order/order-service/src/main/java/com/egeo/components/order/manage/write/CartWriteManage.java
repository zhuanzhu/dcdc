package com.egeo.components.order.manage.write;

import com.egeo.components.order.po.CartItemPO;
import com.egeo.components.order.po.CartPO;


public interface CartWriteManage {

	Long insertCartWithTx(CartPO po);

	int updateCartWithTx(CartPO po);

	int deleteCartWithTx(CartPO po);

	Long saveCartItemWithTx(CartItemPO cartItemPO, Long userId, Integer saleWay, Long storeId,Long clientId);
}
	