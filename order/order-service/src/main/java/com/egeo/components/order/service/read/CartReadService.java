package com.egeo.components.order.service.read;


import java.util.List;

import com.egeo.components.order.dto.CartDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CartReadService {

	public CartDTO findCartById(CartDTO dto);

	public PageResult<CartDTO> findCartOfPage(CartDTO dto,Pagination page);

	public List<CartDTO> findCartAll(CartDTO dto);
	/**
	 * 根据用户id，平台id、客户端id查询用户购物车信息
	 * @param userId
	 * @param platformId
	 * @param clientId
	 * @return
	 */
	public Long findByUserId(Long userId, Long platformId, Long storeId,Integer saleWay,Long clientId);
}
	