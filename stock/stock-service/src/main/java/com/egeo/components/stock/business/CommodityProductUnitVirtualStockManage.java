package com.egeo.components.stock.business;

import java.util.List;
	
import com.egeo.components.stock.dto.CommodityProductUnitVirtualStockDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CommodityProductUnitVirtualStockManage {

	public CommodityProductUnitVirtualStockDTO findCommodityProductUnitVirtualStockById(CommodityProductUnitVirtualStockDTO dto);	

	public PageResult<CommodityProductUnitVirtualStockDTO> findCommodityProductUnitVirtualStockOfPage(CommodityProductUnitVirtualStockDTO dto,Pagination page);

	public List<CommodityProductUnitVirtualStockDTO> findCommodityProductUnitVirtualStockAll(CommodityProductUnitVirtualStockDTO dto);

	Long insertCommodityProductUnitVirtualStockWithTx(CommodityProductUnitVirtualStockDTO dto);

	int updateCommodityProductUnitVirtualStockWithTx(CommodityProductUnitVirtualStockDTO dto);

	int deleteCommodityProductUnitVirtualStockWithTx(CommodityProductUnitVirtualStockDTO dto);
}
	