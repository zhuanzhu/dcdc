package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.JdPriceConfigDTO;


public interface JdPriceConfigWriteService {

	public Long insertJdPriceConfigWithTx(JdPriceConfigDTO dto);

	public int updateJdPriceConfigWithTx(JdPriceConfigDTO dto);

	public int deleteJdPriceConfigWithTx(JdPriceConfigDTO dto);
}
	