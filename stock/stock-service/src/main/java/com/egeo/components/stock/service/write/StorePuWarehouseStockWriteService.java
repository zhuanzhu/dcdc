package com.egeo.components.stock.service.write;

import java.util.List;

import com.egeo.components.stock.dto.StorePuStockRunningWaterDTO;
import com.egeo.components.stock.dto.StorePuWarehouseStockDTO;


public interface StorePuWarehouseStockWriteService {

	public Long insertStorePuWarehouseStockWithTx(StorePuWarehouseStockDTO dto);

	public int updateStorePuWarehouseStockWithTx(StorePuWarehouseStockDTO dto);

	public int deleteStorePuWarehouseStockWithTx(StorePuWarehouseStockDTO dto);
	/**
	 * 批量保存门店pu库存信息
	 * @param storeProductUnitIds
	 * @param standardUnitId
	 * @param l
	 * @param platformId
	 * @return
	 */
	public int insertAllWithTx(List<StorePuWarehouseStockDTO> list);
	/**
	 * 更新门店pu库存信息
	 * @param storePuStockRunningWaterDTOs
	 * @param type 1、采购入库 2、提交订单 3、支付（签收）订单 4、取消订单
	 * @return
	 */
	public int updateStorePuWarehouseStock(List<StorePuStockRunningWaterDTO> storePuStockRunningWaterDTOs,int type);
}
	