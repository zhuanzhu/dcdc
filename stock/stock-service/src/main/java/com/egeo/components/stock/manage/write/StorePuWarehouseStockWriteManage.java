package com.egeo.components.stock.manage.write;

import java.util.List;

import com.egeo.components.stock.po.StorePuStockRunningWaterPO;
import com.egeo.components.stock.po.StorePuWarehouseStockPO;


public interface StorePuWarehouseStockWriteManage {

	Long insertStorePuWarehouseStockWithTx(StorePuWarehouseStockPO po);

	int updateStorePuWarehouseStockWithTx(StorePuWarehouseStockPO po);

	int deleteStorePuWarehouseStockWithTx(StorePuWarehouseStockPO po);
	/**
	 * 批量保存门店pu库存信息(商家默认为1)
	 * @param storeProductUnitIds
	 * @param standardUnitId
	 * @param merchantId
	 * @param platformId
	 * @return
	 */
	int insertAllWithTx(List<StorePuWarehouseStockPO> list);
	/**
	 * 更新门店库存信息
	 * 库存流水类型:1提交订单 2支付 3 取消订单（未支付） 4 取消订单（已支付） 5 进货 6 出货 7签收订单
	 * @param storePuStockRunningWaterDTOs
	 * @param type
	 * @return
	 */
	int updateStorePuWarehouseStock(List<StorePuStockRunningWaterPO> storePuStockRunningWaterPOs, int type);
}
	