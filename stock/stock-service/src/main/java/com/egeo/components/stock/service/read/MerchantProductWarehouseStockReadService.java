package com.egeo.components.stock.service.read;

import java.util.List;

import com.egeo.components.stock.dto.MerchantProductWarehouseStockDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface MerchantProductWarehouseStockReadService {

    MerchantProductWarehouseStockDTO findById(MerchantProductWarehouseStockDTO dto);

    PageResult<MerchantProductWarehouseStockDTO> findPageMerchantProductWarehouseStock(
            Pagination page,MerchantProductWarehouseStockDTO dto);

    List<MerchantProductWarehouseStockDTO> findAll(MerchantProductWarehouseStockDTO dto);

    MerchantProductWarehouseStockDTO findByMerchantProductId(
            MerchantProductWarehouseStockDTO merchantProductWarehouseStockDTO);
    /**
	 * 根据skuid查询sku库存信息
	 * @param dto
	 * @return
	 */
	MerchantProductWarehouseStockDTO findBySkuId(Long skuId,List<Long> commodityProductUnitIds);
}
	