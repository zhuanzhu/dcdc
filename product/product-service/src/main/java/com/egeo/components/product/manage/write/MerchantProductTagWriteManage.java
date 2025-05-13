package com.egeo.components.product.manage.write;

import java.util.List;

import com.egeo.components.product.po.MerchantProductTagPO;


public interface MerchantProductTagWriteManage {

	Long insertMerchantProductTagWithTx(MerchantProductTagPO po);

	int updateMerchantProductTagWithTx(MerchantProductTagPO po);

	int deleteMerchantProductTagWithTx(MerchantProductTagPO po);
	/**
	 * 根据商品id和标签id集合删除商品标签关系
	 * @param tags
	 * @return
	 */
	int delByTags(Long merchantProductId, List<Long> tags);
	/**
	 * 根据商品id删除商品标签关系
	 * @param id
	 * @return
	 */
	int delByMerchantProductId(Long merchantProductId);
}
	