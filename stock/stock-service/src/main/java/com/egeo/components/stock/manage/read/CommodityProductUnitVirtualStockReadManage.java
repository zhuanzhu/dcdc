package com.egeo.components.stock.manage.read;

import java.util.List;
	
import com.egeo.components.stock.po.CommodityProductUnitVirtualStockPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CommodityProductUnitVirtualStockReadManage {

	public CommodityProductUnitVirtualStockPO findCommodityProductUnitVirtualStockById(CommodityProductUnitVirtualStockPO po);

	public PageResult<CommodityProductUnitVirtualStockPO> findCommodityProductUnitVirtualStockOfPage(CommodityProductUnitVirtualStockPO po,Pagination page);

	public List<CommodityProductUnitVirtualStockPO> findCommodityProductUnitVirtualStockAll(CommodityProductUnitVirtualStockPO po);
}
	