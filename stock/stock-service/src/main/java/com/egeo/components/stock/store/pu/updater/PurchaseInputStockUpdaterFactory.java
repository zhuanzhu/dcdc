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
import com.egeo.utils.EmptyUtil;

/**
 * 采购入库库存更新命令的工厂
 * @author min
 *
 */
@Component
public class PurchaseInputStockUpdaterFactory<T> 
		extends AbstractStockUpdaterFactory<T> {
	
	/**
	 * 构造函数
	 * @param storePuWarehouseStockWriteDAO 门店pu库存管理写模块的DAD组件
	 * @param storePuWarehouseStockReadDAO 门店pu库存管理读模块的DAD组件
	 */
	@Autowired
	public PurchaseInputStockUpdaterFactory(
			StorePuWarehouseStockWriteDAO storePuWarehouseStockWriteDAO,
			StorePuWarehouseStockReadDAO storePuWarehouseStockReadDAO,
			StorePuStockRunningWaterWriteDAO storePuStockRunningWaterWriteDAO) {
		super(storePuWarehouseStockWriteDAO,storePuWarehouseStockReadDAO,storePuStockRunningWaterWriteDAO);
	}


	/**
	 * 创建库存更新命令
	 * @param storePuWarehouseStockPOs 门店pu库存PO对象
	 * @return 库存更新命令
	 * @throws Exception
	 */
	@Override
	protected StockUpdater create(
			List<StorePuWarehouseStockPO> storePuWarehouseStockPOs,
			T parameter) throws Exception {
		List<StorePuStockRunningWaterPO> storePuStockRunningWaterPOs = (List<StorePuStockRunningWaterPO>) parameter;
		
		Map<Long, StorePuStockRunningWaterPO> storePuStockRunningWaterPOMap = new HashMap<>();
		
		if(EmptyUtil.isNotEmpty(storePuStockRunningWaterPOs)) {
			for(StorePuStockRunningWaterPO storePuStockRunningWaterPO : storePuStockRunningWaterPOs) {
				storePuStockRunningWaterPOMap.put(storePuStockRunningWaterPO.getStoreProductUnitId(), 
						storePuStockRunningWaterPO);
			}
		}
		
		return new PurchaseInputStockUpdater(
				storePuWarehouseStockPOs, 
				storePuWarehouseStockWriteDAO,
				storePuStockRunningWaterWriteDAO, 
				storePuStockRunningWaterPOMap); 
	}

}
