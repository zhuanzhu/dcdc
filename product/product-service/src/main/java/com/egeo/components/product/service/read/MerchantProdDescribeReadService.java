package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.MerchantProdDescribeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface MerchantProdDescribeReadService {

	public MerchantProdDescribeDTO findMerchantProdDescribeById(MerchantProdDescribeDTO dto);

	public PageResult<MerchantProdDescribeDTO> findMerchantProdDescribeOfPage(MerchantProdDescribeDTO dto,Pagination page);

	public List<MerchantProdDescribeDTO> findMerchantProdDescribeAll(MerchantProdDescribeDTO dto);
}
	