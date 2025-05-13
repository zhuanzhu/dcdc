package com.egeo.components.stock.store.pu.updater;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.stock.dao.read.StorePuWarehouseStockReadDAO;
import com.egeo.components.stock.dao.write.StorePuStockRunningWaterWriteDAO;
import com.egeo.components.stock.dao.write.StorePuWarehouseStockWriteDAO;
import com.egeo.components.stock.po.StorePuStockRunningWaterPO;
import com.egeo.components.stock.po.StorePuWarehouseStockPO;

/**
 * 取消订单库存更新组件工厂
 * @author min
 *
 */
@Component
public class CancelOrderStockUpdaterFactory<T> 
		extends AbstractStockUpdaterFactory<T> {

	/**
	 * 构造函数
	 * @param goodsStockDAO 商品库存管理模块DAO组件
	 * @param dateProvider 日期辅助组件
	 */
	@Autowired
	public CancelOrderStockUpdaterFactory(
			StorePuWarehouseStockWriteDAO storePuWarehouseStockWriteDAO,
			StorePuWarehouseStockReadDAO storePuWarehouseStockReadDAO,
			StorePuStockRunningWaterWriteDAO storePuStockRunningWaterWriteDAO) {  
		super(storePuWarehouseStockWriteDAO, storePuWarehouseStockReadDAO,storePuStockRunningWaterWriteDAO);
	}
	
	/**
	 * 创建商品库存更新组件
	 * @param storePuWarehouseStockPOs 门店pu库存DO对象集合
	 * @param parameter 
	 * @return 商品库存更新组件
	 */
	@Override
	protected StockUpdater create(List<StorePuWarehouseStockPO> storePuWarehouseStockPOs, 
			T parameter) throws Exception {
		List<StorePuStockRunningWaterPO> storePuStockRunningWaterPOs = (List<StorePuStockRunningWaterPO>) parameter;
		Map<Long, StorePuStockRunningWaterPO> storePuStockRunningWaterPOMap = new HashMap<Long, StorePuStockRunningWaterPO>();
		for(StorePuStockRunningWaterPO storePuStockRunningWaterPO : storePuStockRunningWaterPOs) {
			storePuStockRunningWaterPOMap.put(storePuStockRunningWaterPO.getStoreProductUnitId(), storePuStockRunningWaterPO);
		}
		
		return new CancelOrderStockUpdater(storePuWarehouseStockPOs, storePuWarehouseStockWriteDAO, 
				storePuStockRunningWaterPOMap,storePuStockRunningWaterWriteDAO);
	} 

}
