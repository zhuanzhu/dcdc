package com.egeo.components.stock.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.stock.po.StorePuWarehouseStockPO;
import com.egeo.orm.BaseWriteDAO;

public interface StorePuWarehouseStockWriteDAO extends BaseWriteDAO<StorePuWarehouseStockPO> {

	int insertAllWithTx(@Param("list")List<StorePuWarehouseStockPO> list);

}
	