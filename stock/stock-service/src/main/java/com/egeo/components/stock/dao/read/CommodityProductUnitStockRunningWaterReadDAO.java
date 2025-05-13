package com.egeo.components.stock.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.stock.condition.CommodityProductUnitStockRunningWaterCondition;
import com.egeo.components.stock.po.CommodityProductUnitStockRunningWaterPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface CommodityProductUnitStockRunningWaterReadDAO extends BaseReadDAO<CommodityProductUnitStockRunningWaterPO>{

	List<CommodityProductUnitStockRunningWaterPO> findAllByOrderCodes(@Param("orderCodes") List<String> orderCodes);

	List<CommodityProductUnitStockRunningWaterCondition> findConditionOfPage(@Param("po") CommodityProductUnitStockRunningWaterPO po,
			@Param("page") Pagination page);

	List<CommodityProductUnitStockRunningWaterPO> findAllByOrderCodesAndType(@Param("orderCodes") List<String> orderCodes,@Param("type") Integer type);
	
}
	