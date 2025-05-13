package com.egeo.components.product.manage.write;

import java.util.List;

import com.egeo.components.product.po.MerchantProdClientPO;


public interface MerchantProdClientWriteManage {

	Long insertMerchantProdClientWithTx(MerchantProdClientPO po);

	int updateMerchantProdClientWithTx(MerchantProdClientPO po);

	int deleteMerchantProdClientWithTx(MerchantProdClientPO po);
	/**
	 * 根据su草稿与客户端的关系id集合批量删除su草稿与客户端的关系
	 * @param merchantProdClientIds
	 * @return
	 */
	int deleteByMerchantProdClientIdsWithTx(List<Long> merchantProdClientIds);

    void saveMerchantProdClient(List<MerchantProdClientPO> merchantProdClientPOList);
}
	