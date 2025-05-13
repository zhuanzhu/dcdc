package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.MerchantProductCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface MerchantProductCompanyReadService {

	public MerchantProductCompanyDTO findMerchantProductCompanyById(MerchantProductCompanyDTO dto);

	public PageResult<MerchantProductCompanyDTO> findMerchantProductCompanyOfPage(MerchantProductCompanyDTO dto,Pagination page);

	public List<MerchantProductCompanyDTO> findMerchantProductCompanyAll(MerchantProductCompanyDTO dto);
}
	