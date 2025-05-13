package com.egeo.components.stock.store.pu.updater;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.egeo.components.stock.dao.read.StorePuWarehouseStockReadDAO;
import com.egeo.components.stock.dao.write.StorePuStockRunningWaterWriteDAO;
import com.egeo.components.stock.dao.write.StorePuWarehouseStockWriteDAO;
import com.egeo.components.stock.po.StorePuStockRunningWaterPO;
import com.egeo.components.stock.po.StorePuWarehouseStockPO;

/**
 * 库存更新命令工厂的父类
 * 
 * @author min
 *
 */
public abstract class AbstractStockUpdaterFactory<T> implements StockUpdaterFactory<T> {

	private static final Logger logger = LoggerFactory.getLogger(AbstractStockUpdaterFactory.class);

	/**
	 * 门店pu库存管理模块的DAD组件
	 */
	protected StorePuWarehouseStockWriteDAO storePuWarehouseStockWriteDAO;

	protected StorePuWarehouseStockReadDAO storePuWarehouseStockReadDAO;
	/**
	 * 门店库存流水添加组件
	 */
	protected StorePuStockRunningWaterWriteDAO storePuStockRunningWaterWriteDAO;

	/**
	 * 构造函数
	 * 
	 * @param storePuWarehouseStockWriteDAO
	 *            门店pu库存管理模块的DAD组件
	 */
	public AbstractStockUpdaterFactory(
			StorePuWarehouseStockWriteDAO storePuWarehouseStockWriteDAO,
			StorePuWarehouseStockReadDAO storePuWarehouseStockReadDAO,
			StorePuStockRunningWaterWriteDAO storePuStockRunningWaterWriteDAO) {
		this.storePuWarehouseStockWriteDAO = storePuWarehouseStockWriteDAO;
		this.storePuWarehouseStockReadDAO = storePuWarehouseStockReadDAO;
		this.storePuStockRunningWaterWriteDAO = storePuStockRunningWaterWriteDAO;
	}

	/**
	 * 创建库存更新命令
	 */
	public StockUpdater create(T parameter) {
		try {
			List<Long> storePuIds = getStorePuIds(parameter);
			List<StorePuWarehouseStockPO> storePuWarehouseStockPOs = createStorePuWarehouseStockPOs(storePuIds);
			return create(storePuWarehouseStockPOs, parameter);
		} catch (Exception e) {
			logger.error("error", e);
		}
		return null;
	}

	/**
	 * 获取门店Puid集合
	 * 
	 * @return 门店Puid集合
	 * @throws Exception
	 */
	protected List<Long> getStorePuIds(T parameter) throws Exception {
		List<StorePuStockRunningWaterPO> storePuStockRunningWaterPOs = (List<StorePuStockRunningWaterPO>) parameter;
		
		List<Long> storePuIds = new ArrayList<Long>();
		
		for(StorePuStockRunningWaterPO storePuStockRunningWaterPO : storePuStockRunningWaterPOs) {
			storePuIds.add(storePuStockRunningWaterPO.getStoreProductUnitId());
		}
		
		return storePuIds;
	}

	/**
	 * 创建库存更新命令
	 * 
	 * @param storePuWarehouseStockPOs
	 *            门店pu库存PO对象集合
	 * @return 库存更新命令
	 * @throws Exception
	 */
	protected abstract StockUpdater create(List<StorePuWarehouseStockPO> storePuWarehouseStockPOs, T parameter) throws Exception;

	/**
	 * 创建门店pu库存PO对象集合
	 * 
	 * @param storePuIds
	 *            门店puid集合
	 * @return 门店pu库存PO对象集合
	 */
	private List<StorePuWarehouseStockPO> createStorePuWarehouseStockPOs(List<Long> storePuIds) throws Exception {
		List<StorePuWarehouseStockPO> storePuWarehouseStockPOs = new ArrayList<StorePuWarehouseStockPO>(
				storePuIds.size());

		for (Long storePuId : storePuIds) {
			StorePuWarehouseStockPO storePuWarehouseStockPO = storePuWarehouseStockReadDAO.findByStorePuId(storePuId);
			storePuWarehouseStockPOs.add(storePuWarehouseStockPO);
		}

		return storePuWarehouseStockPOs;
	}

}
