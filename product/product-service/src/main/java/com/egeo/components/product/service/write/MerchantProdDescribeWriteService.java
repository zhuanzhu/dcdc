package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.MerchantProdDescribeDTO;

import java.util.List;


public interface MerchantProdDescribeWriteService {

	public Long insertMerchantProdDescribeWithTx(MerchantProdDescribeDTO dto);

	public int updateMerchantProdDescribeWithTx(MerchantProdDescribeDTO dto);

	public int deleteMerchantProdDescribeWithTx(MerchantProdDescribeDTO dto);

    void saveMerchantProdDescribe(List<Long> merchantProductIdList);

    void updateMerchantProdDescribeSWithTx(MerchantProdDescribeDTO merchantProdDescribeDTO);
}
	