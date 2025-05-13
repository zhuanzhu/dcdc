package com.egeo.components.stock.store.pu.updater;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.egeo.components.stock.dao.write.StorePuWarehouseStockWriteDAO;
import com.egeo.components.stock.po.StorePuWarehouseStockPO;

/**
 * 商品库存更新命令的抽象基类
 * @author min
 *
 */
public abstract class AbstractStockUpdater implements StockUpdater {

	private static final Logger logger = LoggerFactory.getLogger(
			AbstractStockUpdater.class);
	
	/**
	 * 门店pu库存PO对象
	 */
	protected List<StorePuWarehouseStockPO> storePuWarehouseStockPOs;
	
	/**
	 * 门店pu库存管理模块的DAD组件
	 */
	protected StorePuWarehouseStockWriteDAO storePuWarehouseStockWriteDAO;
	
	/**
	 * 构造函数
	 * @param storePuWarehouseStockPOs 门店pu库存PO对象
	 * @param storePuWarehouseStockWriteDAO 门店pu库存管理模块的DAD组件
	 */
	public AbstractStockUpdater(
			List<StorePuWarehouseStockPO> storePuWarehouseStockPOs,
			StorePuWarehouseStockWriteDAO storePuWarehouseStockWriteDAO) {
		this.storePuWarehouseStockPOs = storePuWarehouseStockPOs;
		this.storePuWarehouseStockWriteDAO = storePuWarehouseStockWriteDAO;
	}
	
	/**
	 * 更新商品库存
	 */
	public Boolean updateGoodsStock() {
		try {
			updateSaleStockQuantity();
			updateLockedStockQuantity();
			executeUpdateGoodsStock();
			insertStorePuStockRunningWaterPO();
		} catch (Exception e) {
			logger.error("error", e); 
		}
		return true;
	}
	
	/**
	 * 更新商品的销售库存
	 */
	protected abstract void updateSaleStockQuantity() throws Exception;
	
	/**
	 * 更新商品的锁定库存
	 */
	protected abstract void updateLockedStockQuantity() throws Exception;
	
	/**
	 * 实际执行更新商品库存的操作
	 * @throws Exception
	 */
	private void executeUpdateGoodsStock() throws Exception {
		for(StorePuWarehouseStockPO storePuWarehouseStockPO : storePuWarehouseStockPOs) {
			storePuWarehouseStockWriteDAO.update(storePuWarehouseStockPO);
		}
	}
	
	/**
	 * 保存门店库存流水
	 */
	protected abstract void insertStorePuStockRunningWaterPO();
	
}
