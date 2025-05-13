package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.MerchantProdClientDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface MerchantProdClientReadService {

	public MerchantProdClientDTO findMerchantProdClientById(MerchantProdClientDTO dto);

	public PageResult<MerchantProdClientDTO> findMerchantProdClientOfPage(MerchantProdClientDTO dto,Pagination page);

	public List<MerchantProdClientDTO> findMerchantProdClientAll(MerchantProdClientDTO dto);
}
	