package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.MerchantProdAttValueDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface MerchantProdAttValueReadService {

	public MerchantProdAttValueDTO findMerchantProdAttValueById(MerchantProdAttValueDTO dto);

	public PageResult<MerchantProdAttValueDTO> findMerchantProdAttValueOfPage(MerchantProdAttValueDTO dto,Pagination page);

	public List<MerchantProdAttValueDTO> findMerchantProdAttValueAll(MerchantProdAttValueDTO dto);
}
	