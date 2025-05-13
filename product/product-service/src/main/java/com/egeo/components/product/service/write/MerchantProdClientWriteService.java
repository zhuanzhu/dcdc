package com.egeo.components.product.service.write;

import java.util.List;

import com.egeo.components.product.dto.MerchantProdClientDTO;


public interface MerchantProdClientWriteService {

	public Long insertMerchantProdClientWithTx(MerchantProdClientDTO dto);

	public int updateMerchantProdClientWithTx(MerchantProdClientDTO dto);

	public int deleteMerchantProdClientWithTx(MerchantProdClientDTO dto);
	/**
	 * 根据su草稿与客户端的关系id集合批量删除su草稿与客户端的关系
	 * @param merchantProdClientIds
	 * @return
	 */
	public int deleteByMerchantProdClientIdsWithTx(List<Long> merchantProdClientIds);

    void saveMerchantProdClient(List<Long> merchantProductIdList);
}
	