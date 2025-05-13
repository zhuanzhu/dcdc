package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.MerchantProductMembershipDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface MerchantProductMembershipReadService {

	public MerchantProductMembershipDTO findMerchantProductMembershipById(MerchantProductMembershipDTO dto);

	public PageResult<MerchantProductMembershipDTO> findMerchantProductMembershipOfPage(MerchantProductMembershipDTO dto,Pagination page);

	public List<MerchantProductMembershipDTO> findMerchantProductMembershipAll(MerchantProductMembershipDTO dto);
}
	