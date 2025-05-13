package com.egeo.components.order.manage.read;

import java.util.List;
	
import com.egeo.components.order.po.CartPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CartReadManage {

	public CartPO findCartById(CartPO po);

	public PageResult<CartPO> findCartOfPage(CartPO po,Pagination page);

	public List<CartPO> findCartAll(CartPO po);
	/**
	 * 根据用户id，平台id、门店id ,销售方式查询用户购物车信息
	 * @param userId
	 * @param platformId
	 * @param clientId
	 * @return
	 */
	public CartPO findByUserId(Long userId, Long platformId, Long storeId,Integer saleWay,Long clientId);
}
	