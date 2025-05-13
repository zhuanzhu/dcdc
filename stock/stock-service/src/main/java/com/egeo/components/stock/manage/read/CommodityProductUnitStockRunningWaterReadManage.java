package com.egeo.components.stock.manage.read;

import java.util.List;

import com.egeo.components.stock.condition.CommodityProductUnitStockRunningWaterCondition;
import com.egeo.components.stock.po.CommodityProductUnitStockRunningWaterPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CommodityProductUnitStockRunningWaterReadManage {

	public CommodityProductUnitStockRunningWaterPO findCommodityProductUnitStockRunningWaterById(CommodityProductUnitStockRunningWaterPO po);

	public PageResult<CommodityProductUnitStockRunningWaterPO> findCommodityProductUnitStockRunningWaterOfPage(CommodityProductUnitStockRunningWaterPO po,Pagination page);

	public List<CommodityProductUnitStockRunningWaterPO> findCommodityProductUnitStockRunningWaterAll(CommodityProductUnitStockRunningWaterPO po);

	public List<CommodityProductUnitStockRunningWaterPO> findCommodityProductUnitStockRunningWaterByOrderCodes(
			List<String> orderCodes);

	public PageResult<CommodityProductUnitStockRunningWaterCondition> findConditionOfPage(
			CommodityProductUnitStockRunningWaterPO po, Pagination page);

	public List<CommodityProductUnitStockRunningWaterPO> findCommodityProductUnitStockRunningWaterByOrderCodes(
			List<String> orderCodes, Integer status);
	
}
	