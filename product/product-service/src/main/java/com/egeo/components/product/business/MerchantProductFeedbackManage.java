package com.egeo.components.product.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.product.dto.MerchantProductFeedbackDTO;
import com.egeo.components.product.dto.SellPlatformDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface MerchantProductFeedbackManage {

	public MerchantProductFeedbackDTO findMerchantProductFeedbackById(MerchantProductFeedbackDTO dto);	

	public PageResult<MerchantProductFeedbackDTO> findMerchantProductFeedbackOfPage(MerchantProductFeedbackDTO dto,Pagination page);

	public List<MerchantProductFeedbackDTO> findMerchantProductFeedbackAll(MerchantProductFeedbackDTO dto);

	Long insertMerchantProductFeedbackWithTx(MerchantProductFeedbackDTO dto);

	int updateMerchantProductFeedbackWithTx(MerchantProductFeedbackDTO dto);

	int deleteMerchantProductFeedbackWithTx(MerchantProductFeedbackDTO dto);

	boolean insertComparePriceInfo(MerchantProductFeedbackDTO dto);

	Map<String, Object> findComparePrice(SellPlatformDTO dto);

	boolean doFeedbackOperator(MerchantProductFeedbackDTO dto);
}
	