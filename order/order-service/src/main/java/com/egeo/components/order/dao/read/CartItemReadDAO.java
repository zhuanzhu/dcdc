package com.egeo.components.order.dao.read;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.order.po.CartItemPO;
import com.egeo.orm.BaseReadDAO;

public interface CartItemReadDAO extends BaseReadDAO<CartItemPO>{
	/**
	 * 根据购物车id和pu商品id查询购物车信息
	 * @param id
	 * @param productUnitId
	 * @return
	 */
	CartItemPO findByCartIdProductUnitId(@Param("cartId")Long cartId, @Param("productUnitId")Long productUnitId);
	/**
	 * 根据用户id查询购物车pu数量
	 * @param req
	 * @return
	 */
	Integer findCartItemSumByUserId(@Param("userId")Long userId, @Param("platformId")Long platformId, @Param("clientId")Long clientId);
	/**
	 * 根据用户id查询用户购物车pu种类数量
	 * @param userId
	 * @param platformId
	 * @param clientId
	 * @return
	 */
	int findCartItemPUSumByUserId(@Param("userId")Long userId, @Param("platformId")Long platformId, @Param("storeId")Long storeId);

    Integer findCartItemSumByUserIds(@Param("storeId") Long storeId,@Param("userId") Long userId, @Param("platformId")Long platformId, @Param("clientId")Long clientId);
}
	