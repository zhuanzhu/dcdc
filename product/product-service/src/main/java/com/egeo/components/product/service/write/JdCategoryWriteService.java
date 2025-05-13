package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.JdCategoryDTO;


public interface JdCategoryWriteService {

	public Long insertJdCategoryWithTx(JdCategoryDTO dto);

	public int updateJdCategoryWithTx(JdCategoryDTO dto);

	public int deleteJdCategoryWithTx(JdCategoryDTO dto);
}
	