package com.egeo.components.order.service.write;

import java.util.List;

import com.egeo.components.order.dto.CartItemDTO;


public interface CartItemWriteService {

	public Long insertCartItemWithTx(CartItemDTO dto);

	public int updateCartItemWithTx(CartItemDTO dto);

	public int deleteCartItemWithTx(CartItemDTO dto);
	/**
	 * 加入购物车
	 * @param vo
	 * @param req
	 * @return
	 */
	public Long saveCartItemWithTx(CartItemDTO dto, Long userId, Integer saleWay,Long storeId,Long clientId);
	/**
	 * 根据购物车pu商品关系id把购物车数量加一
	 * @param cartItemId
	 * @return
	 */
	public int addNumWithTx(Long cartItemId);
	/**
	 * 根据购物车pu商品关系id把购物车数量减一
	 * @param cartItemId
	 * @return
	 */
	public int minusNumWithTx(Long cartItemId);
	/**
	 * 根据购物车id集合批量删除购物车pu商品关系
	 * @param cartItemIdList
	 * @return
	 */
	public int deleteCartItemByIdsWithTx(List<Long> cartItemIdList);
}
	