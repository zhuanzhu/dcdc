package com.egeo.components.order.business;

import java.util.List;
	
import com.egeo.components.order.dto.CartDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CartManage {

	public CartDTO findCartById(CartDTO dto);	

	public PageResult<CartDTO> findCartOfPage(CartDTO dto,Pagination page);

	public List<CartDTO> findCartAll(CartDTO dto);

	Long insertCartWithTx(CartDTO dto);

	int updateCartWithTx(CartDTO dto);

	int deleteCartWithTx(CartDTO dto);
}
	