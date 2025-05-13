package com.egeo.components.stock.service.read;


import java.util.List;
	
import com.egeo.components.stock.dto.CommodityProductUnitVirtualStockDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CommodityProductUnitVirtualStockReadService {

	public CommodityProductUnitVirtualStockDTO findCommodityProductUnitVirtualStockById(CommodityProductUnitVirtualStockDTO dto);

	public PageResult<CommodityProductUnitVirtualStockDTO> findCommodityProductUnitVirtualStockOfPage(CommodityProductUnitVirtualStockDTO dto,Pagination page);

	public List<CommodityProductUnitVirtualStockDTO> findCommodityProductUnitVirtualStockAll(CommodityProductUnitVirtualStockDTO dto);
}
	