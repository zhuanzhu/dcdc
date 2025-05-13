package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.ShoppingLabelGroupDTO;


public interface ShoppingLabelGroupWriteService {

	public Long insertShoppingLabelGroupWithTx(ShoppingLabelGroupDTO dto);

	public int updateShoppingLabelGroupWithTx(ShoppingLabelGroupDTO dto);

	public int deleteShoppingLabelGroupWithTx(ShoppingLabelGroupDTO dto);
}
	