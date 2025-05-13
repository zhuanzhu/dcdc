package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.SerachSortDTO;


public interface SerachSortWriteService {

	public Long insertSerachSortWithTx(SerachSortDTO dto);

	public int updateSerachSortWithTx(SerachSortDTO dto);

	public int deleteSerachSortWithTx(SerachSortDTO dto);
}
	