package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.MerchantProdDescribeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface MerchantProdDescribeManage {

	public MerchantProdDescribeDTO findMerchantProdDescribeById(MerchantProdDescribeDTO dto);	

	public PageResult<MerchantProdDescribeDTO> findMerchantProdDescribeOfPage(MerchantProdDescribeDTO dto,Pagination page);

	public List<MerchantProdDescribeDTO> findMerchantProdDescribeAll(MerchantProdDescribeDTO dto);

	Long insertMerchantProdDescribeWithTx(MerchantProdDescribeDTO dto);

	int updateMerchantProdDescribeWithTx(MerchantProdDescribeDTO dto);

	int deleteMerchantProdDescribeWithTx(MerchantProdDescribeDTO dto);
}
	