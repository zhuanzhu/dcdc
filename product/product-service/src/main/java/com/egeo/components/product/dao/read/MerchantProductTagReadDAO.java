package com.egeo.components.product.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.condition.MerchantProductTagCondition;
import com.egeo.components.product.po.MerchantProductTagPO;
import com.egeo.orm.BaseReadDAO;

public interface MerchantProductTagReadDAO extends BaseReadDAO<MerchantProductTagPO>{
	/**
	 * 根据su草稿id查询标签信息集合
	 * @param id
	 * @return
	 */
	List<MerchantProductTagCondition> findTagAllByMerchantProductId(@Param("merchantProductId")Long merchantProductId);
}
	