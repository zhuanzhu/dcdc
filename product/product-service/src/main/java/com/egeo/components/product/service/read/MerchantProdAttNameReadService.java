package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.MerchantProdAttNameDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface MerchantProdAttNameReadService {

	public MerchantProdAttNameDTO findMerchantProdAttNameById(MerchantProdAttNameDTO dto);

	public PageResult<MerchantProdAttNameDTO> findMerchantProdAttNameOfPage(MerchantProdAttNameDTO dto,Pagination page);

	public List<MerchantProdAttNameDTO> findMerchantProdAttNameAll(MerchantProdAttNameDTO dto);
}
	