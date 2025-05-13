package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.MerchantProductStoreDTO;

import java.util.List;


public interface MerchantProductStoreWriteService {

	public Long insertMerchantProductStoreWithTx(MerchantProductStoreDTO dto);

	public int updateMerchantProductStoreWithTx(MerchantProductStoreDTO dto);

	public int deleteMerchantProductStoreWithTx(MerchantProductStoreDTO dto);

    void saveMerchantProductStore(List<Long> merchantProductIdList);
}
	