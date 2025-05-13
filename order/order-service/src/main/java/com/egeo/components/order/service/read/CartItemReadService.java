package com.egeo.components.order.service.read;


import java.util.List;
	
import com.egeo.components.order.dto.CartItemDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CartItemReadService {

	public CartItemDTO findCartItemById(CartItemDTO dto);

	public PageResult<CartItemDTO> findCartItemOfPage(CartItemDTO dto,Pagination page);

	public List<CartItemDTO> findCartItemAll(CartItemDTO dto);
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
	