package com.egeo.components.stock.dao.read;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.stock.po.MerchantProductVirtualStockPO;
import com.egeo.orm.BaseReadDAO;

public interface MerchantProductVirtualStockReadDAO extends BaseReadDAO<MerchantProductVirtualStockPO>{

	/**
	 * 根据skuid查询unit库存
	 * @param skuId
	 * @return
	 */
	Long queryUnitStockBySkuId(@Param("skuId")Long skuId);
	/**
	 * 根据skuId从数据库中查询最新的sku商品库存信息
	 * @param id
	 * @return
	 */
	MerchantProductVirtualStockPO merchantProductVirtualStockBySkuId(@Param("skuId")Long skuId);
}
	