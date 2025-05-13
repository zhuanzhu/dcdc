package com.egeo.components.order.service.write;

import com.egeo.components.order.dto.CartDTO;


public interface CartWriteService {

	public Long insertCartWithTx(CartDTO dto);

	public int updateCartWithTx(CartDTO dto);

	public int deleteCartWithTx(CartDTO dto);
}
	