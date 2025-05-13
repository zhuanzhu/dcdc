package com.egeo.components.product.dao.read;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.MerchantProdDescribePO;
import com.egeo.orm.BaseReadDAO;

public interface MerchantProdDescribeReadDAO extends BaseReadDAO<MerchantProdDescribePO>{
	/**
	 * 根据su草稿id查询su草稿详情
	 * @param merchantProductId
	 * @return
	 */
	MerchantProdDescribePO findByMerchantProdId(@Param("merchantProductId")Long merchantProductId);
}
	