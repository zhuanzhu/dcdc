package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.MerchantProdAttValueDTO;

import java.util.List;


public interface MerchantProdAttValueWriteService {

	public Long insertMerchantProdAttValueWithTx(MerchantProdAttValueDTO dto);

	public int updateMerchantProdAttValueWithTx(MerchantProdAttValueDTO dto);

	public int deleteMerchantProdAttValueWithTx(MerchantProdAttValueDTO dto);

    void insertList(List<String> my);
}
	