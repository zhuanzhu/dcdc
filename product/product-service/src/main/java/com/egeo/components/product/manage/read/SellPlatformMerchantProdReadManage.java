package com.egeo.components.product.manage.read;

import java.util.List;

import com.egeo.components.product.condition.SellPlatformMerchantProdCondition;
import com.egeo.components.product.po.SellPlatformMerchantProdPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SellPlatformMerchantProdReadManage {

	public SellPlatformMerchantProdPO findSellPlatformMerchantProdById(SellPlatformMerchantProdPO po);

	public PageResult<SellPlatformMerchantProdPO> findSellPlatformMerchantProdOfPage(SellPlatformMerchantProdPO po,Pagination page);

	public List<SellPlatformMerchantProdPO> findSellPlatformMerchantProdAll(SellPlatformMerchantProdPO po);
	/**
	 * 根据su草稿id查询su草稿比价平台信息
	 * @param sellPlatformMerchantProdDTO
	 * @return
	 */
	public List<SellPlatformMerchantProdCondition> findByMerchantProdId(SellPlatformMerchantProdPO po);
}
	