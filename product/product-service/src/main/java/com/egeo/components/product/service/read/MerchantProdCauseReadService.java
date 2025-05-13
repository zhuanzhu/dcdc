package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.MerchantProdCauseDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface MerchantProdCauseReadService {

	public MerchantProdCauseDTO findMerchantProdCauseById(MerchantProdCauseDTO dto);

	public PageResult<MerchantProdCauseDTO> findMerchantProdCauseOfPage(MerchantProdCauseDTO dto,Pagination page);

	public List<MerchantProdCauseDTO> findMerchantProdCauseAll(MerchantProdCauseDTO dto);
}
	