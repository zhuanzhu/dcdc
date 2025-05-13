package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.MerchantProductStoreDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface MerchantProductStoreReadService {

	public MerchantProductStoreDTO findMerchantProductStoreById(MerchantProductStoreDTO dto);

	public PageResult<MerchantProductStoreDTO> findMerchantProductStoreOfPage(MerchantProductStoreDTO dto,Pagination page);

	public List<MerchantProductStoreDTO> findMerchantProductStoreAll(MerchantProductStoreDTO dto);
}
	