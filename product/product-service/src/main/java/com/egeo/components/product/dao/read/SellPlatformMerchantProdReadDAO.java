package com.egeo.components.product.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.condition.SellPlatformMerchantProdCondition;
import com.egeo.components.product.po.SellPlatformMerchantProdPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface SellPlatformMerchantProdReadDAO extends BaseReadDAO<SellPlatformMerchantProdPO>{
	/**
	 * 根据su草稿id查询su草稿比价平台信息
	 * @param sellPlatformMerchantProdDTO
	 * @return
	 */
	List<SellPlatformMerchantProdCondition> findByMerchantProdId(@Param("po")SellPlatformMerchantProdPO po , @Param("page") Pagination page);
}
	
