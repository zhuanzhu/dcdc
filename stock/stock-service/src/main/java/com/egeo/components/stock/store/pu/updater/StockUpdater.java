package com.egeo.components.stock.store.pu.updater;

/**
 * 商品库存更新命令的接口
 * @author min
 *
 */
public interface StockUpdater {

	/**
	 * 更新商品库存
	 * @return 处理结果
	 */
	Boolean updateGoodsStock();
	
}
