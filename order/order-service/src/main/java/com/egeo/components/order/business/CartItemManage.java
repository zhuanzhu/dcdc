package com.egeo.components.order.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.order.dto.CartItemDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;

public interface CartItemManage {

	public CartItemDTO findCartItemById(Long id);	

	public PageResult<CartItemDTO> findCartItemOfPage(CartItemDTO dto,Pagination page);

	public List<CartItemDTO> findCartItemAll(CartItemDTO dto);

	Long insertCartItemWithTx(CartItemDTO dto);

	int updateCartItemWithTx(CartItemDTO dto);

	int deleteCartItemWithTx(CartItemDTO dto);
	/**
	 * 根据用户id查询购物车pu信息
	 * @param page
	 * @param req
	 * @return
	 */
	public PageResult<Map<String, Object>> findCartItemOfPageByUserId(Long clientId,Long userId,Long companyId, Long platformId, Long storeId,
			Pagination page);
	/**
	 * 加入购物车
	 * @param vo
	 * @param req
	 * @return
	 */
	public JsonResult<String> saveCartItemWithTx(CartItemDTO dto, Long userId, Long platformId , Long storeId, Long clientId,Integer source);
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
	 * 根据用户id查询购物车pu数量
	 * @param req
	 * @return
	 */
	public Integer findCartItemSumByUserId(Long storeId,Long userId, Long platformId, Long clientId);
	/**
	 * 根据用户id查询用户购物车pu种类数量
	 * @param userId
	 * @param platformId
	 * @param clientId
	 * @return
	 */
	public int findCartItemPUSumByUserId(Long userId, Long platformId,Long storeId);
	/**
	 * 根据购物车id集合批量删除购物车pu商品关系
	 * @param cartItemIdList
	 * @return
	 */
	public int deleteCartItemByIdsWithTx(List<Long> cartItemIdList);

    void checkPUStore(Long productUnitId, Long storeId);
}
	