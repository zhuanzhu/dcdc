package com.egeo.components.order.manage.write;

import java.util.List;

import com.egeo.components.order.po.CartItemPO;


public interface CartItemWriteManage {

	Long insertCartItemWithTx(CartItemPO po);

	int updateCartItemWithTx(CartItemPO po);

	int deleteCartItemWithTx(CartItemPO po);
	/**
	 * 根据购物车pu商品关系id把购物车数量加一
	 * @param cartItemId
	 * @return
	 */
	int addNumWithTx(Long cartItemId);
	/**
	 * 根据购物车pu商品关系id把购物车数量减一
	 * @param cartItemId
	 * @return
	 */
	int minusNumWithTx(Long cartItemId);
	/**
	 * 根据购物车puid增加pu数量
	 * @param id
	 * @param num
	 * @return
	 */
	int updateNumById(Long cartItemId, Long num);
	/**
	 * 根据购物车id集合批量删除购物车pu商品关系
	 * @param cartItemIdList
	 * @return
	 */
	int deleteCartItemByIdsWithTx(List<Long> cartItemIdList);
}
	