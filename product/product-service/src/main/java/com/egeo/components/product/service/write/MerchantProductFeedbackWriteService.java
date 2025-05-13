package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.MerchantProductFeedbackDTO;


public interface MerchantProductFeedbackWriteService {

	public Long insertMerchantProductFeedbackWithTx(MerchantProductFeedbackDTO dto);

	public int updateMerchantProductFeedbackWithTx(MerchantProductFeedbackDTO dto);

	public int deleteMerchantProductFeedbackWithTx(MerchantProductFeedbackDTO dto);
}
	