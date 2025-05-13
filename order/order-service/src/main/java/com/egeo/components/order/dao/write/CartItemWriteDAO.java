package com.egeo.components.order.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.order.po.CartItemPO;
import com.egeo.orm.BaseWriteDAO;

public interface CartItemWriteDAO extends BaseWriteDAO<CartItemPO> {
	/**
	 * 根据购物车pu商品关系id把购物车数量加一
	 * @param cartItemId
	 * @return
	 */
	int addNumWithTx(@Param("cartItemId")Long cartItemId);
	/**
	 * 根据购物车pu商品关系id把购物车数量减一
	 * @param cartItemId
	 * @return
	 */
	int minusNumWithTx(@Param("cartItemId")Long cartItemId);
	/**
	 * 根据购物车puid增加pu数量
	 * @param id
	 * @param num
	 * @return
	 */
	int updateNumById(@Param("cartItemId")Long cartItemId, @Param("num")Long num);
	
	/**
	 * 批量删除购物车项
	 * @param cartItemIds
	 * @return
	 */
	int batchDeleteCartItemsByIds(@Param("ids")List<Long> cartItemIds);
}
	