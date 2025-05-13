package com.egeo.components.order.dao.read;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.order.po.CartPO;
import com.egeo.orm.BaseReadDAO;

public interface CartReadDAO extends BaseReadDAO<CartPO>{
	/**
	 * 根据用户id，平台id、客户端id查询用户购物车信息
	 * @param userId
	 * @param platformId
	 * @param clientId
	 * @return
	 */
	CartPO findByUserId(@Param("userId")Long userId, @Param("platformId")Long platformId,
						@Param("storeId") Long storeId,@Param("saleWay") Integer saleWay,@Param("clientId") Long clientId);

}
	