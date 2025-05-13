package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.MerchantProductFeedbackDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface MerchantProductFeedbackReadService {

	public MerchantProductFeedbackDTO findMerchantProductFeedbackById(MerchantProductFeedbackDTO dto);

	public PageResult<MerchantProductFeedbackDTO> findMerchantProductFeedbackOfPage(MerchantProductFeedbackDTO dto,Pagination page);

	public List<MerchantProductFeedbackDTO> findMerchantProductFeedbackAll(MerchantProductFeedbackDTO dto);
}
	