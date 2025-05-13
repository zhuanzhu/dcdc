package com.egeo.components.config.business;

import java.util.Map;

public interface PolymallManage {

	/**
	 * 查询商品
	 * @param paramStr
	 * @return
	 */
	Map<String, Object> queryProduct(String paramStr, String token);
	
	/**
	 * 下载订单
	 * @param paramStr
	 * @return
	 */
	Map<String, Object> queryOrder(String paramStr, String token) throws Exception;
	
	/**
	 * 检查订单状态
	 * @param paramStr
	 * @return
	 */
	Map<String, Object> checkOrderStatus(String paramStr);
	
	/**
	 * 订单发货
	 * @param paramStr
	 * @return
	 */
	Map<String, Object> deliverOrder(String paramStr);
	
	/**
	 * 同步库存
	 * @param paramStr
	 * @return
	 */
	Map<String, Object> syncStock(String paramStr);
	
}
