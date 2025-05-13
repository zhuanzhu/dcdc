package com.egeo.components.order.manage.read;

import java.util.List;
	
import com.egeo.components.order.po.CartItemPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CartItemReadManage {

	public CartItemPO findCartItemById(CartItemPO po);

	public PageResult<CartItemPO> findCartItemOfPage(CartItemPO po,Pagination page);

	public List<CartItemPO> findCartItemAll(CartItemPO po);
	/**
	 * 根据购物车id和pu商品id查询购物车信息
	 * @param id
	 * @param productUnitId
	 * @return
	 */
	public CartItemPO findByCartIdProductUnitId(Long id, Long productUnitId);
	/**
	 * 根据用户id查询购物车pu数量
	 * @param req
	 * @return
	 */
	public Integer findCartItemSumByUserId(Long storeId,Long userId, Long platformId, Long clientId);
	/**
	 * 根据用户id查询用户购物车数量
	 * @param userId
	 * @param platformId
	 * @param clientId
	 * @return
	 */
	public int findCartItemPUSumByUserId(Long userId, Long platformId,Long storeId);
}
	