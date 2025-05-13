package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.JdPriceLimitUploadDTO;


public interface JdPriceLimitUploadWriteService {

	public Long insertJdPriceLimitUploadWithTx(JdPriceLimitUploadDTO dto);

	public int updateJdPriceLimitUploadWithTx(JdPriceLimitUploadDTO dto);

	public int deleteJdPriceLimitUploadWithTx(JdPriceLimitUploadDTO dto);
}
	