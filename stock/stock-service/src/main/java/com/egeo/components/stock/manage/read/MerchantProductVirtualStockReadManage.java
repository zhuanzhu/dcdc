package com.egeo.components.stock.manage.read;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.stock.po.MerchantProductVirtualStockPO;

public interface MerchantProductVirtualStockReadManage {

	/**
	 * 查询unit库存
	 * @param puId
	 * @return
	 */
	Long queryUnitStockBySkuId(@Param("puId")Long puId);
	/**
	 * 根据skuId从数据库中查询最新的sku商品库存信息
	 * @param id
	 * @return
	 */
	MerchantProductVirtualStockPO merchantProductVirtualStockBySkuId(@Param("skuId")Long skuId);
}
	