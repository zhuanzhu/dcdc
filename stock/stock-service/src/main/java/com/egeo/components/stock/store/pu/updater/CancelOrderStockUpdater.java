package com.egeo.components.stock.store.pu.updater;

import java.util.List;
import java.util.Map;

import com.egeo.components.stock.dao.write.StorePuStockRunningWaterWriteDAO;
import com.egeo.components.stock.dao.write.StorePuWarehouseStockWriteDAO;
import com.egeo.components.stock.po.StorePuStockRunningWaterPO;
import com.egeo.components.stock.po.StorePuWarehouseStockPO;

/**
 * 取消订单库存更新组件
 * @author min
 *
 */
public class CancelOrderStockUpdater extends AbstractStockUpdater {

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
     * @param storePuStockRunningWaterWriteDAO
     */
	public CancelOrderStockUpdater(
            List<StorePuWarehouseStockPO> storePuWarehouseStockPOs,
            StorePuWarehouseStockWriteDAO storePuWarehouseStockWriteDAO,
            Map<Long, StorePuStockRunningWaterPO> storePuStockRunningWaterPOMap, StorePuStockRunningWaterWriteDAO storePuStockRunningWaterWriteDAO) {
		super(storePuWarehouseStockPOs, storePuWarehouseStockWriteDAO);
		this.storePuStockRunningWaterPOMap = storePuStockRunningWaterPOMap;
		this.storePuStockRunningWaterWriteDAO = storePuStockRunningWaterWriteDAO;
	}

	/**
	 * 更新销售库存
	 */
	@Override
	protected void updateSaleStockQuantity() throws Exception {
		
	}
	
	/**
	 * 更新锁定库存
	 */
	@Override
	protected void updateLockedStockQuantity() throws Exception {
		for(StorePuWarehouseStockPO storePuWarehouseStockPO : storePuWarehouseStockPOs) {
			StorePuStockRunningWaterPO storePuStockRunningWaterPO = storePuStockRunningWaterPOMap.get(storePuWarehouseStockPO.getStoreProductUnitId());
			// 赋冻结库存修改前后数据
			storePuStockRunningWaterPO.setPreoperativeRealStockNum(storePuWarehouseStockPO.getRealFrozenStockNum());
			storePuStockRunningWaterPO.setOperationBackRealStockNum(storePuWarehouseStockPO.getRealFrozenStockNum() - 
					storePuStockRunningWaterPO.getStockChange());
			
			storePuStockRunningWaterPO.setPreoperativeStockNum(storePuWarehouseStockPO.getRealStockNum());
			storePuStockRunningWaterPO.setOperationBackStockNum(storePuWarehouseStockPO.getRealStockNum());
			
			storePuWarehouseStockPO.setRealFrozenStockNum(storePuWarehouseStockPO.getRealFrozenStockNum() - 
					storePuStockRunningWaterPO.getStockChange()); 
		}
	}
	
	@Override
	protected void insertStorePuStockRunningWaterPO() {
		for(StorePuWarehouseStockPO storePuWarehouseStockPO : storePuWarehouseStockPOs) {
			StorePuStockRunningWaterPO storePuStockRunningWaterPO = 
					storePuStockRunningWaterPOMap.get(storePuWarehouseStockPO.getStoreProductUnitId());
			storePuStockRunningWaterPO.setStockChange(0L);
			storePuStockRunningWaterWriteDAO.insert(storePuStockRunningWaterPO);
		}
		
	}
}
