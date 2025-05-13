package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.ShoppingLabelDTO;


public interface ShoppingLabelWriteService {

	public Long insertShoppingLabelWithTx(ShoppingLabelDTO dto);

	public int updateShoppingLabelWithTx(ShoppingLabelDTO dto);

	public int deleteShoppingLabelWithTx(ShoppingLabelDTO dto);
}
	