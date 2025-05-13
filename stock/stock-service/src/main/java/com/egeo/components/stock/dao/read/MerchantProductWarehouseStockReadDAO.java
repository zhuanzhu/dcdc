package com.egeo.components.stock.dao.read;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.stock.po.MerchantProductWarehouseStockPO;
import com.egeo.orm.BaseReadDAO;

public interface MerchantProductWarehouseStockReadDAO extends BaseReadDAO<MerchantProductWarehouseStockPO>{

    MerchantProductWarehouseStockPO findByMerchantProductId(@Param("po")MerchantProductWarehouseStockPO po);
    /**
	 * 根据skuid查询sku库存信息
	 * @param dto
	 * @return
	 */
	MerchantProductWarehouseStockPO findBySkuId(@Param("skuId")Long skuId);
}
	