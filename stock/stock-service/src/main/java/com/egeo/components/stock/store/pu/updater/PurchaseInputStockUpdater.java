package com.egeo.components.stock.store.pu.updater;

import java.util.List;
import java.util.Map;

import com.egeo.components.stock.dao.write.StorePuStockRunningWaterWriteDAO;
import com.egeo.components.stock.dao.write.StorePuWarehouseStockWriteDAO;
import com.egeo.components.stock.po.StorePuStockRunningWaterPO;
import com.egeo.components.stock.po.StorePuWarehouseStockPO;

/**
 * 采购入库库存更新命令
 * @author min
 *
 */
public class PurchaseInputStockUpdater extends AbstractStockUpdater {

	/**
	 * 更新库存所需信息对象集合
	 */
	private Map<Long, StorePuStockRunningWaterPO> storePuStockRunningWaterPOMap;
	/**
	 * 门店库存流水添加组件
	 */
	protected StorePuStockRunningWaterWriteDAO storePuStockRunningWaterWriteDAO;
	
	
	/**
	 * 构造函数
	 * @param storePuWarehouseStockPOs 门店pu库存PO对象
	 * @param storePuWarehouseStockWriteDAO 门店pu库存管理模块的DAD组件
	 */
	public PurchaseInputStockUpdater(
			List<StorePuWarehouseStockPO> storePuWarehouseStockPOs, 
			StorePuWarehouseStockWriteDAO storePuWarehouseStockWriteDAO,
			StorePuStockRunningWaterWriteDAO storePuStockRunningWaterWriteDAO,
			Map<Long, StorePuStockRunningWaterPO> storePuStockRunningWaterPOMap) {
		super(storePuWarehouseStockPOs, storePuWarehouseStockWriteDAO);
		this.storePuStockRunningWaterPOMap = storePuStockRunningWaterPOMap;
		this.storePuStockRunningWaterWriteDAO = storePuStockRunningWaterWriteDAO;
	}
	
	/**
	 * 更新销售库存
	 */
	@Override
	protected void updateSaleStockQuantity() throws Exception {
		for(StorePuWarehouseStockPO storePuWarehouseStockPO : storePuWarehouseStockPOs) {
			StorePuStockRunningWaterPO storePuStockRunningWaterPO = 
					storePuStockRunningWaterPOMap.get(storePuWarehouseStockPO.getStoreProductUnitId());
			// 赋真实库存修改之前的数据
			storePuStockRunningWaterPO.setPreoperativeStockNum(storePuWarehouseStockPO.getRealStockNum());
			storePuStockRunningWaterPO.setOperationBackStockNum(storePuWarehouseStockPO.getRealStockNum()
					+ storePuStockRunningWaterPO.getStockChange());
			
			storePuStockRunningWaterPO.setPreoperativeRealStockNum(storePuWarehouseStockPO.getRealFrozenStockNum());
			storePuStockRunningWaterPO.setOperationBackRealStockNum(storePuWarehouseStockPO.getRealFrozenStockNum());
			
			storePuWarehouseStockPO.setRealStockNum(storePuWarehouseStockPO.getRealStockNum()
					+ storePuStockRunningWaterPO.getStockChange()); 
		}
	}

	/**
	 * 更新锁定库存
	 */
	@Override
	protected void updateLockedStockQuantity() throws Exception {
		
	}

	@Override
	protected void insertStorePuStockRunningWaterPO() {
		for(StorePuWarehouseStockPO storePuWarehouseStockPO : storePuWarehouseStockPOs) {
			StorePuStockRunningWaterPO storePuStockRunningWaterPO = 
					storePuStockRunningWaterPOMap.get(storePuWarehouseStockPO.getStoreProductUnitId());
			storePuStockRunningWaterWriteDAO.insert(storePuStockRunningWaterPO);
		}
		
	}
	
	

}
