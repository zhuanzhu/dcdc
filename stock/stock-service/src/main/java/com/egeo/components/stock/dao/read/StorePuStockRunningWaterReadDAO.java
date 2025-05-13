package com.egeo.components.stock.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.stock.condition.StorePuStockRunningWaterCondition;
import com.egeo.components.stock.po.StorePuStockRunningWaterPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface StorePuStockRunningWaterReadDAO extends BaseReadDAO<StorePuStockRunningWaterPO>{
	
	public List<StorePuStockRunningWaterCondition> findStorePuStockRunningWaterConditionOfPage(@Param("po")StorePuStockRunningWaterPO po, @Param("page") Pagination page);
	
}
	