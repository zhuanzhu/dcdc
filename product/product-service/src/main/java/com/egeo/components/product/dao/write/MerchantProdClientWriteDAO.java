package com.egeo.components.product.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.MerchantProdClientPO;
import com.egeo.orm.BaseWriteDAO;

public interface MerchantProdClientWriteDAO extends BaseWriteDAO<MerchantProdClientPO> {
	/**
	 * 根据su草稿与客户端的关系id集合批量删除su草稿与客户端的关系
	 * @param merchantProdClientIds
	 * @return
	 */
	int deleteByMerchantProdClientIdsWithTx(@Param("ids")List<Long> merchantProdClientIds);
	/**
	 * 批量保存su商品客户端关系信息
	 * @param merchantProdClients
	 * @return
	 */
	int insertAll(@Param("poList")List<MerchantProdClientPO> merchantProdClients);

    void saveMerchantProdClient(@Param("poList")List<MerchantProdClientPO> merchantProdClientPOList);
}
	