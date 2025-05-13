package com.egeo.components.product.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.MerchantProductTagPO;
import com.egeo.orm.BaseWriteDAO;

public interface MerchantProductTagWriteDAO extends BaseWriteDAO<MerchantProductTagPO> {
	/**
	 * 根据商品id和标签id集合删除商品标签关系
	 * @param tags
	 * @return
	 */
	int delByTags(@Param("merchantProductId")Long merchantProductId,@Param("ids")List<Long> tags);

	int insertAll(@Param("poList")List<MerchantProductTagPO> merchantProductTags);
}
	