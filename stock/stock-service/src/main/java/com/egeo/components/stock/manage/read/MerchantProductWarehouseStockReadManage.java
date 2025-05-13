package com.egeo.components.stock.manage.read;

import java.util.List;

import com.egeo.components.stock.po.MerchantProductWarehouseStockPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface MerchantProductWarehouseStockReadManage {

    MerchantProductWarehouseStockPO findById(MerchantProductWarehouseStockPO po);

    PageResult<MerchantProductWarehouseStockPO> findPage(Pagination page, MerchantProductWarehouseStockPO po);

    List<MerchantProductWarehouseStockPO> findAll(MerchantProductWarehouseStockPO po);

    MerchantProductWarehouseStockPO findByMerchantProductId(MerchantProductWarehouseStockPO po);
    /**
	 * 根据skuid查询sku库存信息
	 * @param dto
	 * @return
	 */
	MerchantProductWarehouseStockPO findBySkuId(Long skuId);
}
	