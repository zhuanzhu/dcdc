package com.egeo.components.stock.business;

import java.util.List;

import com.egeo.components.stock.vo.MerchantProductStock;
import com.egeo.components.stock.vo.MerchantProductWarehouseStockVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface MerchantProductWarehouseStockManage {

    Long saveMerchantProductWarehouseStock(MerchantProductWarehouseStockVO merchantProductWarehouseStockVO);

    Long updateMerchantProductWarehouseStock(MerchantProductWarehouseStockVO merchantProductWarehouseStockVO);

    com.egeo.components.stock.dto.MerchantProductWarehouseStockDTO findById(
            MerchantProductWarehouseStockVO merchantProductWarehouseStockVO);

    PageResult<MerchantProductStock> findPageMerchantProductWarehouseStock(Pagination page,
            MerchantProductWarehouseStockVO merchantProductWarehouseStockVO);

    List<MerchantProductWarehouseStockVO> findAll(
            MerchantProductWarehouseStockVO merchantProductWarehouseStockVO);
	

}
	