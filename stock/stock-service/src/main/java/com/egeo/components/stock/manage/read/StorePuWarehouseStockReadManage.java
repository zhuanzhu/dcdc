package com.egeo.components.stock.manage.read;

import java.util.List;
	
import com.egeo.components.stock.po.StorePuWarehouseStockPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StorePuWarehouseStockReadManage {

	public StorePuWarehouseStockPO findStorePuWarehouseStockById(StorePuWarehouseStockPO po);

	public PageResult<StorePuWarehouseStockPO> findStorePuWarehouseStockOfPage(StorePuWarehouseStockPO po,Pagination page);

	public List<StorePuWarehouseStockPO> findStorePuWarehouseStockAll(StorePuWarehouseStockPO po);
	/**
	 * 根据门店puid查询门店pu库存信息
	 * @param storePuId
	 * @return
	 */
	public StorePuWarehouseStockPO findByStorePuId(Long storePuId);
	/**
	 * 根据门店id获取缺货商品规格数量(门店PU分身的在线库存数量为0的PU数量)
	 * @param storeId
	 * @param platformId
	 * @return
	 */
	public Integer findByStorePuSellOutSum(Long storeId, Long platformId);
	/**
	 * 根据puId集合查询pu库存为0的数量
	 * @param storePuIds
	 * @return
	 */
	public Integer findPuSellOutSumStoreByPuIds(List<Long> storePuIds);
	
	/**
	 * 查询门店商品真实库存
	 * @param storeProductUnitId
	 * @return
	 */
	Long realStockNumByStoreProductUnitId(Long storeProductUnitId, Long storeId);
	
}
	